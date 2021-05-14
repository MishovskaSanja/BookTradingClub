package com.sorsix.booktradingclub.api

import com.sorsix.booktradingclub.api.dto.RequestDto
import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.service.RequestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/requests")
@CrossOrigin("http://localhost:4200")
internal class RequestController(
        val requestService: RequestService
) {

    @GetMapping
    fun getRequests(): List<Request>{
        return requestService.getAllRequests()
    }

    @GetMapping("/accepted")
    fun getTrades(): List<Request>{
        return requestService.getAllAcceptedRequests()
    }

    @PostMapping("/post")
    fun postRequest(@RequestBody requestDto: RequestDto, request: HttpServletRequest) : ResponseEntity<Request>{
       return ResponseEntity.ok(requestService.createRequest(requestDto.booksToGive, requestDto.wantedBooks, request))
    }
    
    @DeleteMapping("/{id}")
    fun deleteRequest(@PathVariable id: Long){
        return requestService.cancelRequest(id)
    }

    @PostMapping("/accept/{id}")
    fun acceptRequest(@PathVariable id: Long){
        return requestService.acceptRequest(id)
    }

    @GetMapping("/my")
    fun getAllIncomingRequests(request: HttpServletRequest){

    }

}