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

fun main() {
    createSessionFactory()

    // selectAll()
    // findById()
    // findByName()
    // insert()
    // update()
    delete()
}

// SELECT ALL
fun selectAll() {
    transaction {
        val list = MemberEntity.all().map { MemberModel(it) }
        list.forEach {
            println(it)
        }
    }
}

// 主キー検索
fun findById() {
    transaction {
        val entity = MemberEntity.findById(2)
        entity?.let { println(MemberModel(it)) }
    }
}

// 主キー以外検索
fun findByName() {
    transaction {
        val entity = MemberEntity.find { MemberTable.name eq "Saburo" }.map { MemberModel(it) }
        entity?.let { println(it) }
    }
}

// Insert
fun insert() {
    transaction {
        val entity = MemberEntity.new { name = "Shiro" }
        println(MemberModel(entity))
    }
}

// Update
fun update() {
    transaction {
        val entity = MemberEntity.findById(4)
        entity?.let { it.name = "Yonro" }
    }
}

// Delete
fun delete() {
    transaction {
        val entity = MemberEntity.findById(4)
        entity?.let { it.delete() }
    }
}
