# CAF MCphone Integration

This folder updates CAF's phone mod to `november521/mcphone` `1.11.2` from upstream commit `cd1a9ec` and targets Minecraft `1.21.1` with NeoForge. The upstream project's own multi-loader and multi-version source layout is retained.

## CAF apps

The 1.21.1 NeoForge client registers the CAF integrations through MCphone's app registry. Their data and screens remain owned by the related CAF mods:

- `caf_status` opens the CAF survival status screen when `tarkov_stamina` is installed.
- `global_market` opens the Global Market screen when `gearsandflesh_market` is installed.
- `daily_quests` opens the daily quest screen when `gearsandflesh_quests` is installed.
- `shop` asks the server to open the SDM shop when `sdmshoprework` is installed.
- `hongye` is the free CAF app shelf for those integrations.

CAF integration apps are optional. Their serverbound open requests require the player to carry a phone. The code calls the related mods through their existing entry points so their inventory, permissions, and screen behavior stay authoritative.

## Build

Use JDK 21 and run the build from `platforms/1.21.1-neoforge`:

```powershell
.\gradlew.bat build
```

The upstream project also includes its other supported platform targets. This repository's CAF integration is registered only in the 1.21.1 NeoForge client.

## Player-created apps

The upstream script app platform and `.app-pkg` package support are retained. Script apps run in MCphone's restricted host; they do not load arbitrary Java classes. A web catalog can distribute approved packages by publishing a signed manifest and package artifacts for the existing server deployment workflow. The signing key should stay server-side, and package review and revocation belong in the publishing service.
