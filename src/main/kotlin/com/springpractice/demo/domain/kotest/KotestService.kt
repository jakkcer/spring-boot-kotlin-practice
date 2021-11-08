package com.springpractice.demo.domain.kotest

class KotestService(private val kotestRepository: KotestRepository) {
    fun createMessage(id: Int): String? {
        if (id < 0) return null
        return kotestRepository.findName(id)?.let { "Hello $it" }
    }
}
