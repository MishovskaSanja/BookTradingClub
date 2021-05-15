package com.sorsix.booktradingclub.repository

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import com.sorsix.booktradingclub.domain.enumeration.BookStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface BookRepository : JpaRepository<Book, Long>{

    fun findAllByOwner(owner: User) : List<Book>
    fun findAllByStatus(status: BookStatus) : List<Book>

}