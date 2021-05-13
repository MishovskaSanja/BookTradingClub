package com.sorsix.booktradingclub.service

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.repository.BookRepository
import com.sorsix.booktradingclub.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional
import kotlin.collections.ArrayList

@Service
class BookService(
        val bookRepository: BookRepository
) {

    fun getAllBooks() : List<Book> = bookRepository.findAll()

    fun createBook(name: String, description: String, request: HttpServletRequest) : Book? {
        val user = request.session.getAttribute("user") as User
        val book = Book(id=0, name = name, description = description, owner = user)
        return this.bookRepository.save(book);
    }


    @Transactional
    fun editBook(id:Long, name: String, description: String): Int {
        return this.bookRepository.editBook(id,name,description)
    }

    fun deleteBook(id: Long) {
        return this.bookRepository.deleteById(id)
    }


}