package com.sorsix.booktradingclub.api

import com.sorsix.booktradingclub.api.dto.BookDto
import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.service.BookService
import jdk.jfr.Description
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/books")
@CrossOrigin( "http://localhost:4200")
class BookController(
        val bookService: BookService
) {
    @GetMapping
    fun getBooks(): List<Book> = bookService.getAllAvailableBooks()



    @PutMapping("/edit/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody bookDto: BookDto): ResponseEntity<Unit> {
        return this.bookService.editBook(id, bookDto.name, bookDto.description).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteBook(@PathVariable id: Long) {
        return this.bookService.deleteBook(id)
    }

}