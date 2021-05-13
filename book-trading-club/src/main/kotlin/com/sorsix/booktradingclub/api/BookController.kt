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
    fun getBooks(): List<Book>{
        return bookService.getAllBooks();
    }

    @PostMapping
    fun addBook(@RequestBody bookDto: BookDto,request: HttpServletRequest): ResponseEntity<Book>{
        val book = bookService.createBook(bookDto.name, bookDto.description, request) ;
        if (book != null){
            return ResponseEntity.ok(book)
        }else{
            return ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/edit/{id}")
    fun updateBook(
            @PathVariable id: Long,
            @RequestBody name: String,
            @RequestBody description: String
    ): ResponseEntity<Int> {
        println(name)
        println(description)
        return this.bookService.editBook(id, name, description).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteHero(@PathVariable id: Long) {
        return this.bookService.deleteBook(id)
    }

}