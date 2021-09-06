# spring-boot-kotlin-practice
## Database作成
```
$ cd docker
$ docker-compose up -d
$ mysql -uroot -h 127.0.0.1 --port 3306 -pmysql
mysql> CREATE DATABASE book_manager;
# migration/202108232031_create_book_manager_tables.sqlでCreateTable実行
# testdata/02.sqlで初期データ挿入
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
