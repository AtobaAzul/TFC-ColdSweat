### Usage info

This template repository can be directly cloned to get you started with a new tfc addon.
There is some example datagen for vanilla and TFC. Using datagen is highly encouraged.

This example repo uses Gradle's Version Catalog found at `gradle/wrapper/libs.versions.toml`.
Adding and managing your dependencies with the catalog is recommended but can be confusing as
some mods have a stupid versioning scheme (for example the used patchouli version is `1.21.1-92-NEOFORGE`)
`gradle.properties` has most of the important mod properties. You'll want to change the mod id, name, group id,
authors, and description.