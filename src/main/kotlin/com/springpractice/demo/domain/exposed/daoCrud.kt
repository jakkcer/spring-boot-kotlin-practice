package com.springpractice.demo.domain.exposed

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun createSessionFactory() {
    Database.connect(
        "jdbc:mysql://127.0.0.1:3306/exposed_example",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "mysql"
    )
}

data class MemberModel(val id: Int, val name: String) {
    constructor(entity: MemberEntity) : this(entity.id.value, entity.name)
}

// SELECT ALL
// fun main() {
//     createSessionFactory()
//     transaction {
//         val list = MemberEntity.all().map { MemberModel(it) }
//         list.forEach {
//             println(it)
//         }
//     }
// }

// 主キー検索
// fun main() {
//     createSessionFactory()
//     transaction {
//         val entity = MemberEntity.findById(2)
//         entity?.let { println(MemberModel(it)) }
//     }
// }

// 主キー以外検索
fun main() {
    createSessionFactory()
    transaction {
        val entity = MemberEntity.find { MemberTable.name eq "Saburo" }.map { MemberModel(it) }
        entity?.let { println(it) }
    }
}
