package com.springpractice.demo.domain.rental

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.springpractice.demo.domain.book.Book
import com.springpractice.demo.domain.book.BookRepository
import com.springpractice.demo.domain.book.BookWithRental
import com.springpractice.demo.domain.enum.RoleType
import com.springpractice.demo.domain.user.User
import com.springpractice.demo.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

internal class RentalServiceTest {
    private val userRepository = mock<UserRepository>()
    private val bookRepository = mock<BookRepository>()
    private val rentalRepository = mock<RentalRepository>()

    private val rentalService = RentalService(userRepository, bookRepository, rentalRepository)

    @Test
    fun `endRental when book is rental then delete to rental`() {
        val userId = 100L
        val bookId = 1000L
        val user = User(userId, "test@test.com", "pass", "kotlin", RoleType.USER)
        val book = Book(bookId, "Kotlin入門", "コトリン太郎", LocalDate.now())
        val rental = Rental(bookId, userId, LocalDateTime.now(), LocalDateTime.MAX)
        val bookWithRental = BookWithRental(book, rental)

        whenever(userRepository.find(any() as Long)).thenReturn(user)
        whenever(bookRepository.findWithRental(any())).thenReturn(bookWithRental)

        rentalService.endRental(bookId, userId)

        verify(userRepository).find(userId)
        verify(bookRepository).findWithRental(bookId)
        verify(rentalRepository).endRental(bookId)
    }

    @Test
    fun `endRental when book is not rental then throw exception`() {
        val userId = 100L
        val bookId = 100L
        val user = User(userId, "test@test.com", "pass", "kotlin", RoleType.USER)
        val book = Book(bookId, "Kotlin入門", "コトリン太郎", LocalDate.now())
        val bookWithRental = BookWithRental(book, null)

        whenever(userRepository.find(any() as Long)).thenReturn(user)
        whenever(bookRepository.findWithRental(any())).thenReturn(bookWithRental)

        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            rentalService.endRental(bookId, userId)
        }

        assertThat(exception.message).isEqualTo("未貸出の商品です bookId:$bookId")

        verify(userRepository).find(userId)
        verify(bookRepository).findWithRental(bookId)
        verify(rentalRepository, times(0)).endRental(any())
    }
}
