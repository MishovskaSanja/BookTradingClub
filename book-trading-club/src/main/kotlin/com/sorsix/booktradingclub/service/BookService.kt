package com.sorsix.booktradingclub.service

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.enumeration.BookStatus
import com.sorsix.booktradingclub.repository.BookRepository
import com.sorsix.booktradingclub.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional
import kotlin.collections.ArrayList

@Service
class BookService(
        val bookRepository: BookRepository,
        val userRepository: UserRepository
) {

    fun getAllAvailableBooks() : List<Book> = bookRepository.findAllByStatus(BookStatus.AVAILABLE)
    fun getAllTakenBooks(): List<Book> = bookRepository.findAllByStatus(BookStatus.TAKEN)

    fun createBook(name: String, description: String, username: String) : Book {
        val user = userRepository.findByUsername(username).get()
        val book = Book(id=0, name = name, description = description, owner = user, status = BookStatus.AVAILABLE)
        return this.bookRepository.save(book);
    }

    @Transactional
    fun editBook(id:Long, name: String, description: String) {
        val book = this.bookRepository.findById(id)
        book.map {
            it.name = name
            it.description = description
        }
         this.bookRepository.save(book.get())

    }

    // TODO: this will only work for books that are not accepted in a request, we maybe need to  fix the logic again
    fun deleteBook(id: Long) {
        return this.bookRepository.deleteById(id)
    }
}