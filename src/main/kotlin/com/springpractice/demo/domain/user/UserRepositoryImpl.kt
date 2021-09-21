package com.springpractice.demo.domain.user

import com.springpractice.demo.infrastructure.database.mapper.UserDynamicSqlSupport
import com.springpractice.demo.infrastructure.database.mapper.UserMapper
import com.springpractice.demo.infrastructure.database.mapper.selectByPrimaryKey
import com.springpractice.demo.infrastructure.database.mapper.selectOne
import com.springpractice.demo.infrastructure.database.record.UserRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectPointsAutowiringInspection")
@Repository
class UserRepositoryImpl(
    private val mapper: UserMapper
) : UserRepository {
    override fun find(email: String): User? {
        val record = mapper.selectOne {
            where(UserDynamicSqlSupport.User.email, isEqualTo(email))
        }
        return record?.let { toModel(it) }
    }

    override fun find(id: Long): User? {
        val record = mapper.selectByPrimaryKey(id)
        return record?.let { toModel(it) }
    }

    private fun toModel(record: UserRecord): User {
        return User(
            record.id!!,
            record.email!!,
            record.password!!,
            record.name!!,
            record.roleType!!,
        )
    }
}
