# spring-boot-kotlin-practice
## Database作成
### 使い捨てのMYSQLを作成するコマンド
databaseは `example` を使う
```
$ docker container run --rm -d -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABSE=example -p 3306:3306 --name mysql mysql
```

## ktlint
**!!! Push前に必ずフォーマットすること !!!**
### 概要
- [ktlint](https://ktlint.github.io/) のデフォルトのルールを適用
- MyBatisGeneratorで自動生成されるものは対象外にした

### 実行方法
#### IntelliJ
- リントチェック： Gradleタスクの `verification > ktlintCheck`
- フォーマット： Gradleタスクの `formatting > ktlintFormat`
#### コマンド
- リントチェック： `./gradlew ktlintCheck`
- フォーマット： `./gradlew ktlintFormat`
