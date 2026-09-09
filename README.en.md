# Ultimate Shield

Minecraft **1.20.1 Forge** utility that only changes the vanilla shield.

- Axes never disable a raised vanilla shield (no cooldown is applied at all)
- Attack with any main-hand item while an off-hand shield is raised; blocking stays up during the swing
- Sword attack cooldown is unchanged
- Full walk / sprint speed while blocking
- Arrows and ghast fireballs still block while you are swinging
- Shield durability does not drop
- Everything is toggleable in `config/ultimateshield-common.toml`

Required on **both** client and server.

Build with JDK 17 after copying `gradlew` + `gradle/` from the Forge 1.20.1 MDK (47.4.10):

```
./gradlew build
```

License: All Rights Reserved. Contact the author to modify or redistribute — permission will be given when asked.
