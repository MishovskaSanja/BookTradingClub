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

    fun createRequest(booksToGiveIds:List<Long>, booksWantedIds: List<Long>, httpServletRequest: HttpServletRequest) : List<Request>{
        val userRequesting = httpServletRequest.session.getAttribute("user") as User

        val booksWanted = bookRepository.findAllById(booksWantedIds)
        val booksToGive = bookRepository.findAllById(booksToGiveIds)

        val collections  = booksWanted.stream()
                .collect(Collectors.groupingBy { it.owner })

        val result : MutableList<Request> = ArrayList()
        collections.keys.stream().forEach {
            collections[it]?.let {
                it1 -> Request(requestId = 0, userRequesting = userRequesting, userReceiving = it, booksToGive = booksToGive, wantedBooks = it1, state = RequestState.PENDING)
            }?.let {
                it2 -> this.requestRepository.save(it2)
            }?.let {
                it3 -> result.add(it3)
            }
        }
        return result
    }

    fun deleteRequest(id: Long){
        return this.requestRepository.deleteById(id)
    }

    //TODO: accepted request becomes a trade, only changing the state to ACCEPTED
    fun acceptRequest(requestId: Long){
        requestRepository.findById(requestId).map {
            if (it.state == RequestState.PENDING){
                this.requestRepository.updateRequest(requestId, RequestState.ACCEPTED)
            }
            //TODO: if the request is accepted, all the books from both sides should be deleted
        }
    }

    //TODO: cancel request
    fun cancelRequest(requestId: Long){
        requestRepository.findById(requestId).map {
            if (it.state == RequestState.CANCELED) {
                logger.warn("Already canceled")
            } else {
                this.requestRepository.updateRequest(requestId, RequestState.CANCELED)
            }
        }
    }
}