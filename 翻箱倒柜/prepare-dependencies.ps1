$ErrorActionPreference = 'Stop'
$dependencies = @(
    @{
        Path = 'libs/petiteinventory-neoforge.jar'
        Url = 'https://cdn.modrinth.com/data/Prhnq8xz/versions/JQYnewaA/PetiteInventory-neoforge-1.21.1-1.1.1.jar'
        Hash = '26ED87AE2AA8D0802152AA189661D3200FB05CEA047B837B487FA81899893999'
    },
    @{
        Path = 'libs/corpse-neoforge.jar'
        Url = 'https://cdn.modrinth.com/data/WrpuIfhw/versions/Zwf8nv8y/corpse-neoforge-1.21.1-1.1.13.jar'
        Hash = '80D984F0A17D968220129CC58EDD5C9F28A5F61A84EC67DCC41228A46DFA4BB1'
    },
    @{
        Path = 'libs/lootr-neoforge.jar'
        Url = 'https://cdn.modrinth.com/data/EltpO5cN/versions/SPOrIzX8/lootr-neoforge-1.21.1-1.11.38.127.jar'
        Hash = '5C74DE4709D1C13B25880FDD241A89D5D9F331346D20EDC76F80CCD4D99DC66C'
    }
)
foreach ($dependency in $dependencies) {
    $target = Join-Path $PSScriptRoot $dependency.Path
    New-Item -ItemType Directory -Path (Split-Path $target) -Force | Out-Null
    if (-not (Test-Path -LiteralPath $target)) {
        Invoke-WebRequest -Uri $dependency.Url -OutFile $target
    }
    if ((Get-FileHash -LiteralPath $target -Algorithm SHA256).Hash -ne $dependency.Hash) {
        throw "Checksum mismatch: $target"
    }
}
Write-Output 'Compile dependencies ready. Compatibility test runtime dependencies are described in the migration notes.'
