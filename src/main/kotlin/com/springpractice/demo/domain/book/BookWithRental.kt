package com.springpractice.demo.domain.book

import com.springpractice.demo.domain.rental.Rental

data class BookWithRental(
    val book: Book,
    val rental: Rental?
) {
    val isRental: Boolean
        get() = rental != null
}
