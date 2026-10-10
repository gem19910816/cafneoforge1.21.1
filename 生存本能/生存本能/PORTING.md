# Survival Instinct: Unofficial NeoForge 1.21.1 Port

This work starts from the user's local Survival Instinct 1.0.2 Forge 1.20.1 JAR.
The original JAR and existing game saves are not modified.
Registry identifiers and original resources are retained wherever compatible.

## Verification Policy

No assertion is made that all bugs are fixed. Every fix must have a documented
reproduction or code-level rationale and a corresponding verification result.
Build success alone is not proof of client, server, multiplayer or modpack compatibility.
Do not deploy an unfinished or unverified JAR into the user's live mods directory.

## Priority Issues

- EMF excessive armor model creation: retain and reuse baked armor models; invalidate on resource reload.
- Replace legacy Forge networking, registrations and item capabilities with NeoForge 1.21.1 APIs.
- Preserve bag contents and reject recursive storage of portable bags.
- Validate server-authoritative dash packets and bound movement impulses.
- Reduce duplicate per-living-entity equipment scans and repeated effect allocations.

## License Note

The original JAR has `license="Academic Free License v3.0"` in `META-INF/mods.toml`.
No standalone license document was found inside that JAR. The public project page
uses a conflicting All Rights Reserved label. This port preserves the JAR declaration
without asserting permission from the author or replacing the original license.
Resolve scope and redistribution permission before public publication.
