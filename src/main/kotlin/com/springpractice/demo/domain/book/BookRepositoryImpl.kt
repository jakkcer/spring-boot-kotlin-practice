package com.springpractice.demo.domain.book

import com.springpractice.demo.domain.rental.Rental
import com.springpractice.demo.infrastructure.database.mapper.BookMapper
import com.springpractice.demo.infrastructure.database.mapper.BookWithRentalMapper
import com.springpractice.demo.infrastructure.database.mapper.deleteByPrimaryKey
import com.springpractice.demo.infrastructure.database.mapper.insert
import com.springpractice.demo.infrastructure.database.mapper.select
import com.springpractice.demo.infrastructure.database.mapper.selectByPrimaryKey
import com.springpractice.demo.infrastructure.database.mapper.updateByPrimaryKeySelective
import com.springpractice.demo.infrastructure.database.record.BookRecord
import com.springpractice.demo.infrastructure.database.record.BookWithRentalRecord
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Suppress("SpringJavaInjectPointsAutowiringInspection")
@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
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

    override fun register(book: Book) {
        bookMapper.insert(toRecord(book))
    }

    private fun toRecord(model: Book): BookRecord {
        return BookRecord(model.id, model.title, model.author, model.releaseDate)
    }

    override fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?) {
        bookMapper.updateByPrimaryKeySelective(BookRecord(id, title, author, releaseDate))
    }

    override fun delete(id: Long) {
        bookMapper.deleteByPrimaryKey(id)
    }
}
