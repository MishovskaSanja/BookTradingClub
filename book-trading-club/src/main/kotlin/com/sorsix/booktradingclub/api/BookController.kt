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

    @PostMapping("/addBook")
    fun addBook(@RequestBody bookDto: BookDto): ResponseEntity<Book>{
        val book = bookService.createBook(bookDto.name, bookDto.description, bookDto.imgUrl)
        return ResponseEntity.ok(book)
    }

    @PutMapping("/edit/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody bookDto: BookDto) {
        this.bookService.editBook(id, bookDto.name, bookDto.description, bookDto.imgUrl)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteBook(@PathVariable id: Long) {
        this.bookService.deleteBook(id)
    }


}