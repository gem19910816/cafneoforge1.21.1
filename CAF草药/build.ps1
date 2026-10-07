# Crop Expansion - NeoForge 1.21.1 build script
#
# NOTE: this file is intentionally ASCII-only. Windows PowerShell 5.1 reads
#       BOM-less files as ANSI/GBK, which would corrupt non-ASCII text.
#       Machine-specific paths (which contain Chinese) live in local.properties.
#
# Usage: double-click build.bat, or run .\build.ps1 from PowerShell.

$ErrorActionPreference = 'Stop'
$root = $PSScriptRoot
Set-Location -LiteralPath $root

function Read-Props([string]$file) {
    $h = @{}
    if (Test-Path -LiteralPath $file) {
        foreach ($line in (Get-Content -LiteralPath $file -Encoding UTF8)) {
            $t = $line.Trim()
            if ($t -and -not $t.StartsWith('#') -and $t.Contains('=')) {
                $i = $t.IndexOf('=')
                $h[$t.Substring(0, $i).Trim()] = $t.Substring($i + 1).Trim()
            }
        }
    }
    return $h
}

function Fail([string]$msg) {
    Write-Host ""
    Write-Host "BUILD FAILED: $msg" -ForegroundColor Red
    exit 1
}

$local = Read-Props (Join-Path $root 'local.properties')

# ---- JDK 21 ----
$jdk = $local['java_home']
if (-not $jdk) { $jdk = $env:JAVA_HOME }
if (-not $jdk -or -not (Test-Path -LiteralPath (Join-Path $jdk 'bin\java.exe'))) {
    Fail "JDK 21 not found. Set 'java_home' in local.properties."
}

# ---- shared Gradle ----
$gradleBin = $local['gradle_bin']
if (-not $gradleBin -or -not (Test-Path -LiteralPath $gradleBin)) {
    Fail "gradle.bat not found. Set 'gradle_bin' in local.properties."
}
$gradleHome = $local['gradle_user_home']
if (-not $gradleHome) { Fail "Set 'gradle_user_home' in local.properties." }

$env:JAVA_HOME = $jdk
if (-not $env:GRADLE_OPTS) { $env:GRADLE_OPTS = '-Xmx3G' }

Write-Host "JDK        : $jdk" -ForegroundColor DarkGray
Write-Host "Gradle     : $gradleBin" -ForegroundColor DarkGray
Write-Host "GradleHome : $gradleHome" -ForegroundColor DarkGray
Write-Host ""

# Do not pipe or capture gradle output - let it print straight to the console.
& $gradleBin -p $root --no-daemon -g $gradleHome build
if ($LASTEXITCODE -ne 0) { Fail "gradle build reported errors (see above)." }

# ---- locate the produced jar ----
$libs = Join-Path $root 'build\libs'
$jar = Get-ChildItem -LiteralPath $libs -Filter '*.jar' -File -ErrorAction SilentlyContinue |
       Where-Object { $_.Name -notlike '*-sources.jar' } |
       Sort-Object LastWriteTime | Select-Object -Last 1
if (-not $jar) { Fail "No jar produced under $libs" }

$sizeKB = [math]::Round($jar.Length / 1KB, 1)
Write-Host ""
Write-Host "BUILD OK: build\libs\$($jar.Name)  ($sizeKB KB)" -ForegroundColor Green

# ---- archive to the mod-save folder (per the folder-structure spec) ----
$deploy = $local['deploy_dir']
if ($deploy) {
    if (Test-Path -LiteralPath $deploy) {
        Copy-Item -LiteralPath $jar.FullName -Destination (Join-Path $deploy $jar.Name) -Force
        Write-Host "Archived to: $deploy" -ForegroundColor Green
    } else {
        Write-Host "NOTE: deploy folder does not exist, skipped -> $deploy" -ForegroundColor DarkYellow
    }
}

# ---- deploy into the test instance's mods folder ----
$deployMods = $local['deploy_mods']
if ($deployMods) {
    if (Test-Path -LiteralPath $deployMods) {
        # Drop older builds of this mod so only one copy is loaded.
        # A jar locked by a running game cannot be deleted - warn instead of failing.
        $stale = Get-ChildItem -LiteralPath $deployMods -Filter 'crop_expansion-*.jar' -File -ErrorAction SilentlyContinue
        foreach ($f in $stale) {
            if ($f.Name -eq $jar.Name) { continue }
            try {
                Remove-Item -LiteralPath $f.FullName -Force -ErrorAction Stop
                Write-Host "Removed old build: $($f.Name)" -ForegroundColor DarkGray
            } catch {
                Write-Host "NOTE: could not remove old $($f.Name) (game running?)" -ForegroundColor DarkYellow
            }
        }
        $target = Join-Path $deployMods $jar.Name
        try {
            Copy-Item -LiteralPath $jar.FullName -Destination $target -Force -ErrorAction Stop
            Write-Host "Deployed to: $target" -ForegroundColor Green
        } catch {
            Write-Host "NOTE: could NOT deploy to $target (game running?)" -ForegroundColor Red
        }
    } else {
        Write-Host "NOTE: instance mods folder does not exist, skipped -> $deployMods" -ForegroundColor DarkYellow
    }
}

Write-Host ""
Write-Host "Next: start the test instance (NeoForge 1.21.1) and check the mod list." -ForegroundColor Cyan
exit 0
