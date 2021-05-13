package com.sorsix.booktradingclub.repository

import com.sorsix.booktradingclub.domain.Request
import com.sorsix.booktradingclub.domain.enumeration.RequestState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RequestRepository: JpaRepository<Request, Long> {

    @Modifying
    @Query("update Request r set r.state = :state  where r.id = :id")
    fun updateRequest(id: Long, state: RequestState): Int

    fun getAllByState(state: RequestState): List<Request>
}