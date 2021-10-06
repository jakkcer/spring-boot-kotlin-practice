package com.springpractice.demo.domain.book

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class BookServiceTest {
    private val bookRepository = mock<BookRepository>()

    private val bookService = BookService(bookRepository)

    @Test
    fun `getList when book list is exist then return list`() {
        val book = Book(1, "Kotlin入門", "コトリン太郎", LocalDate.now())
        val bookWithRental = BookWithRental(book, null)
        val excepted = listOf(bookWithRental)

        whenever(bookRepository.findAllWithRental()).thenReturn(excepted)

        val result = bookService.getList()
        assertThat(result).isEqualTo(excepted)
    }
}
