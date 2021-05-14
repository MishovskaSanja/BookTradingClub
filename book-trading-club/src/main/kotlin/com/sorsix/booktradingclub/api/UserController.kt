package com.sorsix.booktradingclub.api

import com.sorsix.booktradingclub.api.dto.UserEditDto
import com.sorsix.booktradingclub.api.dto.UserLoginDto
import com.sorsix.booktradingclub.api.dto.UserRegisterDto
import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.exception.InvalidCredentialsException
import com.sorsix.booktradingclub.domain.exception.UsernameAlreadyExistsException
import com.sorsix.booktradingclub.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/api/user")
@CrossOrigin( "http://localhost:4200")
class UserController(
        val userService: UserService
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
    fun login(@RequestBody userLoginDto : UserLoginDto, request: HttpServletRequest) : ResponseEntity<String>{
        return userService.login(userLoginDto.username, userLoginDto.password).map {
            request.session.setAttribute("user", it);
            ResponseEntity.ok("Success!");
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
    fun logout(request: HttpServletRequest) : Unit {
        request.session.invalidate()
    }

    @PutMapping("/edit")
    fun editInfo(@RequestBody userDto: UserEditDto, request: HttpServletRequest) : ResponseEntity<User>{
        return this.userService.editInfo(userDto.fullName, userDto.city, userDto.address, userDto.state, request)
                .map {
                    ResponseEntity.ok(it)
                }.orElseGet{
                    ResponseEntity.badRequest().build()
                }
    }

    @GetMapping("/userBooks")
    fun getUserBooks(httpServletRequest: HttpServletRequest): ResponseEntity<List<Book>>{
        return ResponseEntity.ok(this.userService.findAllUserBooks(httpServletRequest));
    }
}