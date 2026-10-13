# CAF_Herbs - NeoForge 1.21.1 build entry
#
# NOTE: intentionally ASCII-only. Windows PowerShell 5.1 reads BOM-less files as
#       ANSI/GBK, which would corrupt non-ASCII text.
#
# This script only runs the project-local Gradle wrapper and produces
# build\libs\*.jar. It contains no absolute paths and copies nothing outside the
# project - archiving and installing into a test instance are done manually
# (spec 4.2 / 4.3 / 5.2).
#
# Requires JAVA_HOME to point at a JDK 21.
#
# Usage: .\build.ps1              -> gradlew build
#        .\build.ps1 clean build  -> gradlew clean build

$ErrorActionPreference = 'Stop'
$root = $PSScriptRoot
Set-Location -LiteralPath $root

if (-not $env:JAVA_HOME) {
    Write-Host "WARNING: JAVA_HOME is not set - Gradle needs a JDK 21." -ForegroundColor DarkYellow
}

if ($args.Count -eq 0) {
    & "$root\gradlew.bat" build
} else {
    & "$root\gradlew.bat" @args
}
exit $LASTEXITCODE
