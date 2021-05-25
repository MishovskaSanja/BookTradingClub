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
        val userService: UserService
) {

    fun getAllAvailableBooks() : List<Book> = bookRepository.findAllByStatus(BookStatus.AVAILABLE)

    fun createBook(name: String, description: String, imgUrl:String) : Book {
        val user = this.userService.getCurrentUser()
        val book = Book(id=0, name = name, description = description, imgUrl = imgUrl, owner = user, status = BookStatus.AVAILABLE)
        return this.bookRepository.save(book);
    }

    //edna kniga mozhe da se promeni samo ako e dostapna, i mozhe da se promeni samo od nejziniot owner!!!
    fun editBook(id:Long, name: String, description: String, imgUrl: String) {
        val book = this.bookRepository.findById(id)
        book.map {
            if (it.status == BookStatus.AVAILABLE && it.owner == userService.getCurrentUser()) {
                it.name = name
                it.description = description
                it.imgUrl = imgUrl
            } else{
                throw RuntimeException("Book can't be edited!") //BookCantBeEditedException
            }
        }?: run {
            throw RuntimeException("Book doesn't exist!") //BookIdDoesntExistException
        }
         this.bookRepository.save(book.get())
    }

    //edna kniga mozhe da se izbrishe samo ako e dostapna, i mozhe da se izbrishe samo od nejziniot owner!!!
    fun deleteBook(id: Long) {
        val book = this.bookRepository.findById(id)
        book.map {
            if (it.status == BookStatus.AVAILABLE && it.owner == userService.getCurrentUser()) {
                this.bookRepository.deleteById(id)
            }else{
                throw RuntimeException("Book can't be deleted!") //BookCantBeDeleted
            }
        }?: run{
            throw RuntimeException("Book doesn't exist!") //BookIdDoesntExist
        }
    }
}