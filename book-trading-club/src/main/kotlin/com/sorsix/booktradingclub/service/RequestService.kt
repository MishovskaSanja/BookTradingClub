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
        val bookRepository: BookRepository
) {

    val logger = LoggerFactory.getLogger(Logger::class.java)

    fun getAllRequests(): List<Request> = requestRepository.getAllByState(RequestStatus.PENDING)

    fun getAllAcceptedRequests(): List<Request> = requestRepository.getAllByState(RequestStatus.ACCEPTED)


    fun createRequest(bookToGiveId: Long, bookWantedId: Long, httpServletRequest: HttpServletRequest): Optional<Request> {
        val userRequesting = httpServletRequest.session.getAttribute("user") as User

        val bookWanted: Book = bookRepository.findById(bookWantedId).get()
        val bookToGive: Book = bookRepository.findById(bookToGiveId).get()

        return if (bookWanted.status == BookStatus.AVAILABLE && bookToGive.status == BookStatus.AVAILABLE) {
            val userReceiving = bookWanted.owner
            val request = Request(requestId = 0, userRequesting = userRequesting, userReceiving = userReceiving, bookToGive = bookToGive, wantedBook = bookWanted, status = RequestStatus.PENDING)
            this.requestRepository.save(request)
            Optional.of(request)
        } else {
            logger.warn("Status of the books wanted: [{}], to give: [{}]", bookWanted.status, bookToGive.status)
            return Optional.empty()
        }
    }

    fun cancelRequest(id: Long) {
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
                this.requestRepository.findById(requestId).map { it2 ->
                    this.requestRepository.save(Request(requestId = it2.requestId, userRequesting = it2.userRequesting, userReceiving = it2.userReceiving,
                            wantedBook = it2.wantedBook, bookToGive = it2.bookToGive, status = RequestStatus.ACCEPTED))
                }
            }
            //TODO: if the request is accepted, all the books from both sides should be deleted
            // s: i added new prop to the entity book ( status ), we dont have to delete them
        }
        //TODO: some exception here!!!
    }
}