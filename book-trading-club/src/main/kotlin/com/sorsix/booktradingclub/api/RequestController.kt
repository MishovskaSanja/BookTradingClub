package com.sorsix.booktradingclub.api

import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.service.RequestService
import org.springframework.web.bind.annotation.*

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
    
    @DeleteMapping("/{id}")
    fun deleteRequest(@PathVariable id: Long){
        return requestService.deleteRequest(id)
    }

}