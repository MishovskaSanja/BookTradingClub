package com.sorsix.booktradingclub.service

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.repository.BookRepository
import com.sorsix.booktradingclub.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.ArrayList

@Service
class UserService(
        val userRepository: UserRepository,
        val bookRepository: BookRepository
){

    fun register(username: String, password: String, repeatPassword: String,
                    fullName: String, city: String, state: String, address: String) : Optional<User>{

        return Optional.of(this.userRepository.save(User(username, password, fullName, city, state, address )));
    }

    fun login(username: String, password: String) : Optional<User>{
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    fun editInfo(fullName: String, city: String, state: String, address: String, request: HttpServletRequest) : User{
        val user: User = request.session.getAttribute("user") as User
        user.fullName = fullName;
        user.city = state
        user.address = address
        user.state = state
        return this.userRepository.save(user);
    }

    fun findAllBooks(request: HttpServletRequest): List<Book> {
        try{
            val user: User = request.session.getAttribute("user") as User
            val result =  this.bookRepository.findAllByOwner(user.username)
            return result
        }catch (e: Exception){
            println("null e")
            return ArrayList<Book>();
        }
    }
}