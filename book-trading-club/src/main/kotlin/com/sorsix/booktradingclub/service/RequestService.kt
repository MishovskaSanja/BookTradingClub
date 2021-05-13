package com.sorsix.booktradingclub.service
import com.sorsix.booktradingclub.api.dto.RequestDto
import com.sorsix.booktradingclub.api.dto.UserDto
import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.RequestState
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.repository.RequestRepository
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Service
class RequestService (
        val requestRepository: RequestRepository
){
    val logger: Logger = LoggerFactory.getLogger(RequestService::class.java)

    fun getAllRequests(): List<Request> = requestRepository.getAllByState(RequestState.PENDING)

    fun getAllAcceptedRequests(): List<Request> = requestRepository.getAllByState(RequestState.ACCEPTED)

    //TODO: create request
    fun createRequest(user1: User, books1:List<Book>,
                      user2:User, books2: List<Book>  ): Request{
        val request = Request(0,user1,user2,books1,books2, RequestState.PENDING)
       return this.requestRepository.save(request)
    }

    //TODO: delete request
    fun deleteRequest(id: Long){
        return this.requestRepository.deleteById(id)
    }

    //TODO: accept request becomes a trade, only changing the state to ACCEPTED
    fun acceptRequest(request: Request){
        if (request.state == RequestState.PENDING){
            this.requestRepository.updateRequest(request.requestedId, RequestState.ACCEPTED)
        }
        else{
            logger.warn("The request is: [{}]", request.state)
        }
    }

    //TODO: cancel request
    fun cancelRequest(request: Request){
        if (request.state != RequestState.CANCELED){
            logger.warn("already canceled")
        }
        else{
            this.requestRepository.updateRequest(request.requestedId, RequestState.CANCELED)
        }
    }

    fun deleteCanceledRequests(){
        //TODO()
    }
}