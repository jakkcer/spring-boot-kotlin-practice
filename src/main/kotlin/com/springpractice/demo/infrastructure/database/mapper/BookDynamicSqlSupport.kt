/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.springpractice.demo.infrastructure.database.mapper

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.SqlTable

object BookDynamicSqlSupport {
    object Book : SqlTable("book") {
        val id = column<Long>("id", JDBCType.BIGINT)

        val title = column<String>("title", JDBCType.VARCHAR)

        val author = column<String>("author", JDBCType.VARCHAR)

        val releaseDate = column<Date>("release_date", JDBCType.DATE)
    }
}