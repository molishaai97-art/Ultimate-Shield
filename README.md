# Ultimate Shield

Minecraft **1.20.1 / Forge** mod.

クールダウンがない、そしてガード中に剣などで攻撃できる盾にする Mod です。

バニラの盾だけを強化します。他 Mod の盾には触れません。

## できること

- 斧（プレイヤー・ヴィンディケーター含む）で盾が割れない。クールダウン自体が発生しない
- オフハンド盾を構えたまま、メインハンドの武器で攻撃できる（振り中もガード継続）
- 武器種は問わない。剣の攻撃クールダウンはバニラのまま
- ガード中でも通常移動・ダッシュができる
- 攻撃中でも矢・ガスト火球などを防げる
- 盾の耐久値が減らない
- `config/ultimateshield-common.toml` で全項目 ON/OFF

クライアントとサーバーの**両方**に入れてください（マルチ対応）。

## ビルド

1. [Forge 1.20.1 MDK](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html)（推奨 47.4.10）を展開する
2. MDK の `gradle/` と `gradlew` / `gradlew.bat` をこのフォルダへコピーする
3. JDK 17 を入れ、このフォルダで:

```
./gradlew build
```

成果物: `build/libs/ultimateshield-1.0.0.jar`

Minecraft の `mods` フォルダへ入れて起動してください。

## 設定

`config/ultimateshield-common.toml`

| キー | デフォルト | 内容 |
| --- | --- | --- |
| enabled | true | Mod 全体のスイッチ |
| preventAxeDisable | true | 斧で盾が無効化されない |
| preventShieldCooldown | true | 盾にアイテムクールダウンを付けない |
| attackWhileBlocking | true | ガード中にメインハンド攻撃 |
| noBlockingSlowdown | true | ガード中の移動低下を消す |
| allowSprintWhileBlocking | true | ガード中ダッシュ |
| infiniteDurability | true | 盾の耐久を減らさない |

## 他 Mod との相性

- 対象は `minecraft:shield` のみ。Better Combat / Shield Overhaul などが追加する盾は変更しません
- `disableShield` と `ItemCooldowns.addCooldown` は `@Inject` のみ（`@Overwrite` なし）
- 耐久は Forge の `ShieldBlockEvent` を使用

競合した場合は該当キーを `false` にして切り分けてください。

## ライセンス

All Rights Reserved.

改変・再配布したい場合は作者（[molishaai97-art](https://github.com/molishaai97-art/Ultimate-Shield)）に一声かけてください。許可します。
