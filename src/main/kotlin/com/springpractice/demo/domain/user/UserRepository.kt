package com.springpractice.demo.domain.user

interface UserRepository {
    fun find(email: String): User?
    fun find(id: Long): User?
}
