package com.springpractice.demo.domain.rental

import java.time.LocalDateTime

data class Rental(
    val bookId: Long,
    val userId: Long,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime
)
