package com.sorsix.booktradingclub.service

import com.sorsix.booktradingclub.api.dto.JwtResponse
import com.sorsix.booktradingclub.api.dto.UserEditDto
import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.enumeration.BookStatus
import com.sorsix.booktradingclub.domain.enumeration.RequestStatus
import com.sorsix.booktradingclub.domain.exception.NoAuthenticatedUserException
import com.sorsix.booktradingclub.domain.exception.UsernameAlreadyExistsException
import com.sorsix.booktradingclub.repository.BookRepository
import com.sorsix.booktradingclub.repository.RequestRepository
import com.sorsix.booktradingclub.repository.UserRepository
import com.sorsix.booktradingclub.security.JwtToken
import com.sorsix.booktradingclub.security.UserDetailsImpl
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@Service
class UserService(
        val userRepository: UserRepository,
        val bookRepository: BookRepository,
        val authenticationManager: AuthenticationManager,
        val jwtToken: JwtToken,
        val passwordEncoder: PasswordEncoder,
        val requestRepository: RequestRepository
){
    fun findAllUsers() : List<User>{
        return this.userRepository.findAll();
    }

    fun register(username: String, password: String,
                 fullName: String, city: String, state: String, address: String, imgUrl:String) : Optional<User>{
        return if (!userRepository.existsByUsername(username)) {
            Optional.of(this.userRepository.save(User(username, passwordEncoder.encode(password), fullName, city, state, address, imgUrl)));
        }else{
            throw UsernameAlreadyExistsException("User with username $username already exists!")
        }
    }

    fun authenticateUser(username: String, password: String) : JwtResponse{
            val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            SecurityContextHolder.getContext().authentication = authentication
            val token = jwtToken.generateJwtToken(authentication)
            val userDetails: UserDetailsImpl = authentication.principal as UserDetailsImpl
            return JwtResponse(token, userDetails.username)
    }

    fun getCurrentUser() : User{
        try{
            val principal = SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl
            return userRepository.findByUsername(principal.username).get()
        }catch (e: Exception){
            throw NoAuthenticatedUserException("There is no authenticated user")
        }
    }

    fun editInfo(fullName: String, city: String, address: String, state: String, imgUrl: String
    ) {
        val user = getCurrentUser()
        user.fullName = fullName
        user.address= address
        user.state = state
        user.city = city
        user.imgUrl = imgUrl
        this.userRepository.save(user)
    }

    fun findAllAvailableUserBooks() : List<Book>{
        return getCurrentUser().let {
            this.bookRepository.findAllByOwner(it).stream().filter { it2 -> it2.status == BookStatus.AVAILABLE }.collect(Collectors.toList())
        }
    }

    fun findAllTakenUserBooks() : List<Book>{
        return getCurrentUser().let {
            this.bookRepository.findAllByOwner(it).stream().filter { it2 -> it2.status == BookStatus.TAKEN }.collect(Collectors.toList())
        }
    }

    fun findAllUserBooks(): List<Book> {
        return getCurrentUser().let {
            this.bookRepository.findAllByOwner(it)
        }
    }
    fun findAllByOwnerAndStatus(username: String): List<Book>{
        return bookRepository.findAllByOwnerUsernameAndStatus(username, BookStatus.AVAILABLE);
    }

    fun getIncomingRequests(): Optional<List<Request>>{
        return getCurrentUser().let {
            this.requestRepository.getAllByUserReceiving(it)
        }
    }

    fun getMyRequests() : Optional<List<Request>>{
        return getCurrentUser().let {
            this.requestRepository.getAllByUserRequesting(it).map { it.stream().filter { it2 -> it2.status == RequestStatus.PENDING}.collect(Collectors.toList()) }
        }
    }
}