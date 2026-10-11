$ErrorActionPreference = 'Stop'
$dependencies = @(
    @{
        Path = 'libs/tacz.jar'
        Url = 'https://www.cursemaven.com/curse/maven/timeless-and-classics-zero-1028108/6518539/timeless-and-classics-zero-1028108-6518539.jar'
        Hash = 'D6F38DF90CA9C672FE2039A2ECD7A8D51AAEF43C278FEF70326D744EB7111783'
    },
    @{
        Path = 'libs/tag-editor.jar'
        Url = 'https://www.cursemaven.com/curse/maven/tag-editor-1401631/7365181/tag-editor-1401631-7365181.jar'
        Hash = '0B19A5B1C7F0BA13404E94FD3F89E72A68DD210B77C581231905D467B210BC1E'
    },
    @{
        Path = 'libs/petiteinventory.jar'
        Url = 'https://cdn.modrinth.com/data/Prhnq8xz/versions/JQYnewaA/PetiteInventory-neoforge-1.21.1-1.1.1.jar'
        Hash = '26ED87AE2AA8D0802152AA189661D3200FB05CEA047B837B487FA81899893999'
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
Write-Output 'Compile dependencies ready. Legacy TaCZ and Tag Editor jars must not be installed at runtime.'
