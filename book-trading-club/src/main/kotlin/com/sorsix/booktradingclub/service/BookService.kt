package com.sorsix.booktradingclub.service

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.enumeration.BookStatus
import com.sorsix.booktradingclub.domain.exception.BookNotFoundException
import com.sorsix.booktradingclub.domain.exception.UserAndBookOwnerDoNotMatchException
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

    fun editBook(id:Long, name: String, description: String, imgUrl: String) {
        val book = this.bookRepository.findById(id)
        book.map {
            if (it.status == BookStatus.AVAILABLE && it.owner == userService.getCurrentUser()) {
                it.name = name
                it.description = description
                it.imgUrl = imgUrl
                this.bookRepository.save(it)
            } else{
               throw UserAndBookOwnerDoNotMatchException("Permission to edit $it.name denied.")
            }
        }?: run {
            throw BookNotFoundException("Book with id $id can't be found.")
        }
    }

    fun deleteBook(id: Long) {
        val book = this.bookRepository.findById(id)
        book.map {
            if (it.status == BookStatus.AVAILABLE && it.owner == userService.getCurrentUser()) {
                this.bookRepository.deleteById(id)
            }else{
                throw UserAndBookOwnerDoNotMatchException("Permission to delete $it.name denied.")
            }
        }?: run{
            throw BookNotFoundException("Book with id $id can't be found.")
        }
    }
}