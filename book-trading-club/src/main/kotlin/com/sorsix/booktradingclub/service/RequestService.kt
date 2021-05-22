package com.sorsix.booktradingclub.service

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.enumeration.BookStatus
import com.sorsix.booktradingclub.domain.enumeration.RequestStatus
import com.sorsix.booktradingclub.repository.BookRepository
import com.sorsix.booktradingclub.repository.RequestRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest


@Service
class RequestService(
        val requestRepository: RequestRepository,
        val bookRepository: BookRepository,
        val userService: UserService
) {

    fun getAllRequests(): List<Request> = requestRepository.getAllByStatus(RequestStatus.PENDING)

    fun getAllAcceptedRequests(): List<Request> = requestRepository.getAllByStatus(RequestStatus.ACCEPTED)

    fun findBookById(id: Long) : Book{
        return bookRepository.findById(id).orElseThrow{RuntimeException("Book not found")}
    }

    fun createRequest(bookToGiveId: Long, bookWantedId: Long): Optional<Request> {
        val userRequesting = userService.getCurrentUser()

        val bookWanted: Book = findBookById(bookWantedId)
        val bookToGive: Book = findBookById(bookToGiveId)

        return if (bookWanted.status == BookStatus.AVAILABLE && bookToGive.status == BookStatus.AVAILABLE) {
            val userReceiving = bookWanted.owner
            val request = Request(requestId = 0, userRequesting = userRequesting, userReceiving = userReceiving, bookToGive = bookToGive, wantedBook = bookWanted, status = RequestStatus.PENDING)
            this.requestRepository.save(request)
            Optional.of(request)
        } else {
            return Optional.empty()
        }
    }

    fun deleteRequest(id: Long) {
        return this.requestRepository.deleteById(id)
    }

    fun acceptRequest(requestId: Long) {
        requestRepository.findById(requestId).map { it ->
            if (it.status == RequestStatus.PENDING) {
                this.bookRepository.findById(it.bookToGive.id).map {
                    it.status = BookStatus.TAKEN
                }
                this.bookRepository.findById(it.wantedBook.id).map {
                    it.status = BookStatus.TAKEN
                }
                this.requestRepository.save(Request(requestId = it.requestId, userRequesting = it.userRequesting, userReceiving = it.userReceiving,
                            wantedBook = it.wantedBook, bookToGive = it.bookToGive, status = RequestStatus.ACCEPTED))
                }
            }
        }

}