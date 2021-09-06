package com.springpractice.demo.domain.book

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>

    fun findWithRental(id: Long): BookWithRental?
}
