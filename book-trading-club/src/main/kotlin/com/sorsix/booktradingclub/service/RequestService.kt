package com.sorsix.booktradingclub.service

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.enumeration.RequestState
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.repository.BookRepository
import com.sorsix.booktradingclub.repository.RequestRepository
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest


@Service
class RequestService (
        val requestRepository: RequestRepository,
        val bookRepository: BookRepository
){

    val logger = LoggerFactory.getLogger(Logger::class.java)

    fun getAllRequests(): List<Request> = requestRepository.getAllByState(RequestState.PENDING)

    fun getAllAcceptedRequests(): List<Request> = requestRepository.getAllByState(RequestState.ACCEPTED)

    fun createRequest(bookToGiveId:Long, bookWantedId: Long, httpServletRequest: HttpServletRequest) : Request{
        val userRequesting = httpServletRequest.session.getAttribute("user") as User

        val bookWanted : Book = bookRepository.findById(bookWantedId).get()
        val bookToGive : Book = bookRepository.findById(bookToGiveId).get()

        val userReceiving = bookWanted.owner

        val request = Request(requestId = 0, userRequesting = userRequesting, userReceiving = userReceiving, bookToGive = bookToGive, wantedBook = bookWanted, state = RequestState.PENDING)
        return this.requestRepository.save(request)
    }

    fun cancelRequest(id: Long){
        return this.requestRepository.deleteById(id)
    }

    fun acceptRequest(requestId: Long){
        requestRepository.findById(requestId).map { it ->
            if (it.state == RequestState.PENDING){
                this.bookRepository.deleteById(it.bookToGive.id)
                this.bookRepository.deleteById(it.wantedBook.id)
                this.requestRepository.findById(requestId).map {it2 ->
                    this.requestRepository.save(Request(requestId = it2.requestId, userRequesting = it2.userRequesting, userReceiving =  it2.userReceiving, wantedBook =  it2.wantedBook, bookToGive = it2.bookToGive, RequestState.ACCEPTED))
                }
            }
            //TODO: if the request is accepted, all the books from both sides should be deleted
        }
    }
}