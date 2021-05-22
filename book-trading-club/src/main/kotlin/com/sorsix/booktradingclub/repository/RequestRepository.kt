package com.sorsix.booktradingclub.repository

import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.enumeration.RequestStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RequestRepository: JpaRepository<Request, Long> {
    fun getAllByStatus(status: RequestStatus): List<Request>

    fun getAllByUserRequesting(user: User): Optional<List<Request>>

    fun getAllByUserReceiving(user: User): Optional<List<Request>>
}