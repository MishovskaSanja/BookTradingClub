package com.sorsix.booktradingclub.api

import com.sorsix.booktradingclub.api.dto.BookDto
import com.sorsix.booktradingclub.api.dto.UserEditDto
import com.sorsix.booktradingclub.api.dto.UserLoginDto
import com.sorsix.booktradingclub.api.dto.UserRegisterDto
import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.exception.InvalidCredentialsException
import com.sorsix.booktradingclub.domain.exception.UsernameAlreadyExistsException
import com.sorsix.booktradingclub.service.BookService
import com.sorsix.booktradingclub.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/api/user")
@CrossOrigin( "http://localhost:4200")
class UserController(
        val userService: UserService,
        val bookService: BookService
){

    @PostMapping("/register")
    fun register(@RequestBody user: UserRegisterDto) : ResponseEntity<User>{
        return userService.register(user.username, user.password, user.fullName, user.city, user.state, user.address)
                .map { ResponseEntity.ok().body(it) }.orElseThrow{
                    UsernameAlreadyExistsException()
                }
    }

    @ExceptionHandler(UsernameAlreadyExistsException::class)
    fun handleException(exception: UsernameAlreadyExistsException) : ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Username already exists")
    }

    @PostMapping("/login")
    fun login(@RequestBody userLoginDto : UserLoginDto, request: HttpServletRequest) : ResponseEntity<User>{
        return userService.login(userLoginDto.username, userLoginDto.password).map {
            request.session.setAttribute("user", it);
            ResponseEntity.ok(it);
        }.orElseThrow{
           InvalidCredentialsException()
        }
    }

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleException(exception: InvalidCredentialsException) : ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Invalid credentials!")
    }

    @GetMapping
    fun getAllUsers() : ResponseEntity<List<User>>{
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest) : ResponseEntity<String> {
        request.session.invalidate()
        return ResponseEntity.ok("success!")
    }

    @PutMapping("/edit")
    fun editInfo(@RequestBody userDto: UserEditDto) : ResponseEntity<User>{
        return ResponseEntity.ok(this.userService.editInfo(userDto.username, userDto.fullName, userDto.city, userDto.address, userDto.state))
    }

    @PostMapping("/addBook")
    fun addBook(@RequestBody bookDto: BookDto): ResponseEntity<Book>{
        val book = bookService.createBook(bookDto.name, bookDto.description, bookDto.username)
        return ResponseEntity.ok(book)
    }

    @GetMapping("/userBooks")
    fun getUserBooks(@RequestParam username: String): ResponseEntity<List<Book>>{
        return ResponseEntity.ok(this.userService.findAllUserBooks(username))
    }

    @GetMapping("/incomingRequests")
    fun getIncomingRequests(request: HttpServletRequest) : ResponseEntity<List<Request>>{
        return userService.getIncomingRequests(request).map {
            ResponseEntity.ok(it)
        }.orElseGet{
            ResponseEntity.badRequest().build()
        }
    }
}