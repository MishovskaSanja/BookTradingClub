package com.sorsix.booktradingclub.api

import com.sorsix.booktradingclub.api.dto.BookDto
import com.sorsix.booktradingclub.api.dto.UserEditDto
import com.sorsix.booktradingclub.api.dto.UserLoginDto
import com.sorsix.booktradingclub.api.dto.UserRegisterDto
import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.enumeration.BookStatus
import com.sorsix.booktradingclub.domain.exception.InvalidCredentialsException
import com.sorsix.booktradingclub.domain.exception.RequestAlreadyExists
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
        val userService: UserService
){

    @PostMapping("/register")
    fun register(@RequestBody user: UserRegisterDto) : ResponseEntity<User>{
        return userService.register(user.username, user.password, user.fullName, user.city, user.state, user.address, user.imgUrl)
                .map { ResponseEntity.ok().body(it) }.orElseThrow{
                    UsernameAlreadyExistsException("User with username alrwady exists")
                }
    }

    @ExceptionHandler(UsernameAlreadyExistsException::class)
    fun handleException(exception: UsernameAlreadyExistsException) : ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Username already exists")
    }



    @PostMapping("/login")
    fun login(@RequestBody userLoginDto : UserLoginDto, request: HttpServletRequest) : ResponseEntity<Any>{
        return ResponseEntity.ok(userService.authenticateUser(userLoginDto.username, userLoginDto.password))
    }

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleException(exception: InvalidCredentialsException) : ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Invalid credentials!")
    }

    @GetMapping
    fun getAllUsers() : ResponseEntity<List<User>>{
        return ResponseEntity.ok(userService.findAllUsers());
    }


    @PutMapping("/edit")
    fun editInfo(@RequestBody userDto: UserEditDto) : ResponseEntity<Any>{
        print("here")
        this.userService.editInfo(userDto.fullName, userDto.city, userDto.address, userDto.state, userDto.imgUrl)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/currentUserBooks")
    fun getCurrentUserBooks(): ResponseEntity<List<Book>>{
        return ResponseEntity.ok(this.userService.findAllUserBooks())
    }

    @GetMapping("/userBooks/{id}")
    fun getUserBooks(@PathVariable id: String):ResponseEntity<List<Book>>{
        return ResponseEntity.ok(this.userService.findAllByOwnerAndStatus(id))
    }

    @GetMapping("/takenUserBooks")
    fun getTakenUserBooks(): ResponseEntity<List<Book>>{
        return ResponseEntity.ok(this.userService.findAllTakenUserBooks())
    }

    @GetMapping("/availableUserBooks")
    fun getAvailableUserBooks(): ResponseEntity<List<Book>>{
        return ResponseEntity.ok(this.userService.findAllAvailableUserBooks())
    }

    @GetMapping("/incomingRequests")
    fun getIncomingRequests() : ResponseEntity<List<Request>>{
        return userService.getIncomingRequests().map {
            ResponseEntity.ok(it)
        }.orElseGet{
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/myRequests")
    fun getMyRequests() : ResponseEntity<List<Request>>{
        return userService.getMyRequests().map {
            ResponseEntity.ok(it)
        }.orElseGet{
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/info")
    fun getCurrentUserInfo() : ResponseEntity<User>{
        return ResponseEntity.ok(userService.getCurrentUser())
    }
}