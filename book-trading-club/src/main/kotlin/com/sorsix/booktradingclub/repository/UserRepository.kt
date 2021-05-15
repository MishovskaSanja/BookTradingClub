package com.sorsix.booktradingclub.repository

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.swing.text.html.Option
import javax.transaction.Transactional

@Repository
public interface UserRepository : JpaRepository<User, Long>{

    fun findByUsernameAndPassword(username: String, password: String) : Optional<User>

    fun findByUsername(username: String) : Optional<User>


}
