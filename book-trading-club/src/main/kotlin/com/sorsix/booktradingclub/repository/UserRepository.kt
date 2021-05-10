package com.sorsix.booktradingclub.repository

import com.sorsix.booktradingclub.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.swing.text.html.Option

@Repository
public interface UserRepository : JpaRepository<User, Long>{

    fun findByUsernameAndPassword(username: String, password: String) : Optional<User>

    fun findByUsername(username: String)

}