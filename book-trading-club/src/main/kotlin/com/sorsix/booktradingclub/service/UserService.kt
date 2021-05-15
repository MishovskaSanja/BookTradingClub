package com.sorsix.booktradingclub.service

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.repository.BookRepository
import com.sorsix.booktradingclub.repository.RequestRepository
import com.sorsix.booktradingclub.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional
import kotlin.collections.ArrayList

@Service
class UserService(
        val userRepository: UserRepository,
        val bookRepository: BookRepository,
        val requestRepository: RequestRepository
){

    fun findAllUsers() : List<User>{
        return this.userRepository.findAll();
    }

    fun register(username: String, password: String,
                    fullName: String, city: String, state: String, address: String) : Optional<User>{
        return if (userRepository.findByUsername(username).isEmpty) {
            Optional.of(this.userRepository.save(User(username, password, fullName, city, state, address)));
        }else{
            Optional.empty()
        }
    }

    fun login(username: String, password: String) : Optional<User>{
        return this.userRepository.findByUsernameAndPassword(username, password)
    }

    @Transactional
    fun editInfo(fullName: String, city: String, address: String, state: String, request: HttpServletRequest) {
        val user: User = request.session.getAttribute("user") as User
        user.fullName = fullName
        user.address= address
        user.state = state
        user.city = city
        this.userRepository.save(user)
    }

    fun findAllUserBooks(request: HttpServletRequest): List<Book> {
        val user: User = request.session.getAttribute("user") as User
        return this.bookRepository.findAllByOwner(user)
    }

    fun getIncomingRequests(request: HttpServletRequest): Optional<List<Request>>{
        val user: User = request.session.getAttribute("user") as User
        return this.requestRepository.getAllByUserReceiving(user)
    }
}