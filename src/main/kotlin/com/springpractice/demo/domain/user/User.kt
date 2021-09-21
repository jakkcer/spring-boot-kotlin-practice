package com.springpractice.demo.domain.user

import com.springpractice.demo.domain.enum.RoleType

data class User(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType,
)
