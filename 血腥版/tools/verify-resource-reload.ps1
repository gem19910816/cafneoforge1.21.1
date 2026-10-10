#Requires -Version 7.0
param([string]$JarPath, [string]$OriginalJar)
$ErrorActionPreference = 'Stop'
Add-Type -AssemblyName System.IO.Compression.FileSystem
$root = Split-Path -Parent $PSScriptRoot
if (-not $JarPath) { $JarPath = Join-Path $root 'gore_edition-0.5-neoforge-1.21.1.jar' }
$resources = Join-Path $root 'src/main/resources'
function Read-Entry($Entry) {
    $stream = $Entry.Open()
    $buffer = [IO.MemoryStream]::new()
    try { $stream.CopyTo($buffer); return ,$buffer.ToArray() }
    finally { $stream.Dispose(); $buffer.Dispose() }
}
$zip = [IO.Compression.ZipFile]::OpenRead($JarPath)
try {
    $duplicate = @($zip.Entries | Group-Object FullName | Where-Object Count -GT 1)
    if ($duplicate.Count) { throw 'Duplicate ZIP entry paths' }
    $entries = @($zip.Entries | Where-Object {
        $_.FullName -match '^assets/gore_edition/(geo|animations)/.*\.json$'
    })
    foreach ($entry in $entries) {
        if ($entry.FullName -cnotmatch '^assets/[a-z0-9_.-]+/[a-z0-9/._-]+$') {
            throw "Illegal resource path: $($entry.FullName)"
        }
        $bytes = Read-Entry $entry
        $null = ConvertFrom-Json -InputObject ([Text.Encoding]::UTF8.GetString($bytes))
    }
    $names = @(
        'geo/skeleton_corpse_without_left_arm_ii.geo.json',
        'geo/skeleton_corpse_without_right_arm_ii.geo.json',
        'animations/skeleton_corpse_without_left_arm_ii.animation.json',
        'animations/skeleton_corpse_without_right_arm_ii.animation.json',
        'animations/exarrack_hydra.animation.json',
        'animations/exarrack_hydra_legacy.animation.json'
    )
    foreach ($name in $names) {
        $path = "assets/gore_edition/$name"
        $entry = $zip.GetEntry($path)
        if (-not $entry) { throw "Missing resource: $path" }
        $source = [IO.File]::ReadAllBytes((Join-Path $resources $path))
        # Git may normalize line endings; compare JSON values rather than bytes.
        $a = ConvertFrom-Json -AsHashtable -InputObject ([Text.Encoding]::UTF8.GetString($source))
        $b = ConvertFrom-Json -AsHashtable -InputObject ([Text.Encoding]::UTF8.GetString((Read-Entry $entry)))
        if (($a | ConvertTo-Json -Depth 100 -Compress) -cne ($b | ConvertTo-Json -Depth 100 -Compress)) {
            throw "Source/JAR resource mismatch: $path"
        }
    }
    $badSource = @(Get-ChildItem -LiteralPath (Join-Path $resources 'assets/gore_edition/geo'), (Join-Path $resources 'assets/gore_edition/animations') -File -Recurse -Filter '*.json' |
        Where-Object { $_.Name -cnotmatch '^[a-z0-9._-]+$' })
    if ($badSource.Count) { throw 'Illegal source Geo/animation JSON file name' }
    if ($OriginalJar) {
        $original = [IO.Compression.ZipFile]::OpenRead($OriginalJar)
        try {
            $classes = @($original.Entries | Where-Object FullName -Match '\.class$')
            if ($classes.Count -ne @($zip.Entries | Where-Object FullName -Match '\.class$').Count) {
                throw 'Class entry count changed'
            }
            foreach ($entry in $classes) {
                $current = $zip.GetEntry($entry.FullName)
                if (-not $current -or [Convert]::ToBase64String((Read-Entry $entry)) -cne
                    [Convert]::ToBase64String((Read-Entry $current))) {
                    throw "Code class changed: $($entry.FullName)"
                }
            }
            "PASS: all $($classes.Count) code classes unchanged."
        } finally { $original.Dispose() }
    }
    "PASS: $($entries.Count) packaged Geo/animation JSON resources valid; six repaired resources match source."
    'This static check does not prove in-game resource-pack switching completes.'
} finally { $zip.Dispose() }
