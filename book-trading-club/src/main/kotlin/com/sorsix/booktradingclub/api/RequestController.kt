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

    //shtikliram books, od niv mozhe da zemem id i toa sa owner receiver
    @PostMapping("/makeRequest")
    fun makeRequest(@RequestBody requestDto: RequestDto, request: HttpServletRequest) : ResponseEntity<List<Request>>{
       return ResponseEntity.ok(requestService.createRequest(requestDto.booksToGive, requestDto.wantedBooks, request))
    }
    
    @DeleteMapping("/{id}")
    fun deleteRequest(@PathVariable id: Long){
        return requestService.deleteRequest(id)
    }

}