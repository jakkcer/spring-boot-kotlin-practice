package com.springpractice.demo.domain.rental

interface RentalRepository {
    fun startRental(rental: Rental)

    fun endRental(bookId: Long)
}
