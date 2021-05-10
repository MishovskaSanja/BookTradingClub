package com.sorsix.booktradingclub.api

import com.sorsix.booktradingclub.api.dto.UserDto
import com.sorsix.booktradingclub.api.dto.UserLoginDto
import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/api/user")
class UserController(
        val userService: UserService
){

    @PostMapping
    fun register(@RequestBody user: User) : ResponseEntity<User>{
        return userService.register(user.username, user.password, user.password, user.fullName, user.city, user.state, user.address)
                .map { ResponseEntity.ok().body(it) }.orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    fun login(@RequestBody userLoginDto : UserLoginDto, httpServletRequest: HttpServletRequest) : ResponseEntity<String>{
        return userService.login(userLoginDto.username, userLoginDto.password).map {
            httpServletRequest.session.setAttribute("user", it);
             ResponseEntity.ok("Success!");
        }.orElseGet{
            ResponseEntity.badRequest().body("Not successful!")
        }
    }

    @PostMapping("/edit")
    fun editInfo(@RequestBody userDto: UserDto, request: HttpServletRequest) : ResponseEntity<User>{
        return ResponseEntity.ok(this.userService.editInfo(userDto.fullName, userDto.address, userDto.city, userDto.state, request));
    }

    @GetMapping("/userBooks")
    fun getUserBooks(httpServletRequest: HttpServletRequest): ResponseEntity<List<Book>>{
        return ResponseEntity.ok(this.userService.findAllBooks(httpServletRequest));
    }
}