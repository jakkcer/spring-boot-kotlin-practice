package com.springpractice.demo.web

import com.springpractice.demo.domain.adminbook.AdminBookService
import com.springpractice.demo.domain.book.Book
import com.springpractice.demo.web.form.RegisterBookRequest
import com.springpractice.demo.web.form.UpdateBookRequest
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin/book")
@CrossOrigin
class AdminBookController(
    private val adminBookService: AdminBookService
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterBookRequest) {
        adminBookService.register(
            Book(
                request.id,
                request.title,
                request.author,
                request.releaseDate
            )
        )
    }

    @PutMapping("/update")
    fun update(@RequestBody request: UpdateBookRequest) {
        adminBookService.update(request.id, request.title, request.author, request.releaseDate)
    }

    @DeleteMapping("/delete/{book_id}")
    fun delete(@PathVariable("book_id") bookId: Long) {
        adminBookService.delete(bookId)
    }
}
