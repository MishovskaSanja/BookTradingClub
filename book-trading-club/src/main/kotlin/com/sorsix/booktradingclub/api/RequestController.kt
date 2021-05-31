package com.sorsix.booktradingclub.api

import com.sorsix.booktradingclub.api.dto.RequestDto
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.exception.RequestAlreadyExists
import com.sorsix.booktradingclub.service.RequestService
import com.sorsix.booktradingclub.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/requests")
@CrossOrigin("http://localhost:4200")
internal class RequestController(
        val requestService: RequestService,
        val userService: UserService
) {

    @GetMapping
    fun getRequests(): List<Request> {
        return requestService.getAllRequests()
    }

    @GetMapping("/accepted")
    fun getTrades(): List<Request> {
        return requestService.getAllAcceptedRequests()
    }

    @PostMapping("/post")
    fun postRequest(@RequestBody requestDto: RequestDto): ResponseEntity<Request> {
        return requestService.createRequest(requestDto.bookToGive, requestDto.wantedBook).map {
            ResponseEntity.ok(it)
        }.orElseThrow {
            RequestAlreadyExists("Request for these books already exists.")
        }
    }

    @ExceptionHandler(RequestAlreadyExists::class)
    fun handleException(exception: RequestAlreadyExists): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(exception.message)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteRequest(@PathVariable id: Long) {
        return requestService.deleteRequest(id)
    }

    @PostMapping("/accept/{id}")
    fun acceptRequest(@PathVariable id: Long) {
        return requestService.acceptRequest(id)
    }

}