package com.sorsix.booktradingclub.repository

import com.sorsix.booktradingclub.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long>{
    fun findByUsername(username: String) : Optional<User>
    fun existsByUsername(username: String) : Boolean
    fun findByUsernameAndPassword(username: String, password: String): Optional<User>
}
