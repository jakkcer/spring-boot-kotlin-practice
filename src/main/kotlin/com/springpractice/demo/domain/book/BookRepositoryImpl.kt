package com.springpractice.demo.domain.book

import com.springpractice.demo.domain.rental.Rental
import com.springpractice.demo.infrastructure.database.mapper.BookWithRentalMapper
import com.springpractice.demo.infrastructure.database.mapper.select
import com.springpractice.demo.infrastructure.database.mapper.selectByPrimaryKey
import com.springpractice.demo.infrastructure.database.record.BookWithRentalRecord
import org.springframework.stereotype.Repository

@Suppress("SpringJacaInjectPointsAutowiringInspection")
@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper
) : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map { toModel(it) }
    }

    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(
            record.id!!,
            record.title!!,
            record.author!!,
            record.releaseDate!!
        )
        val rental = record.userId?.let {
            Rental(
                record.id!!,
                record.userId!!,
                record.rentalDatetime!!,
                record.returnDeadline!!
            )
        }
        return BookWithRental(book, rental)
    }

    override fun findWithRental(id: Long): BookWithRental? {
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }
}
