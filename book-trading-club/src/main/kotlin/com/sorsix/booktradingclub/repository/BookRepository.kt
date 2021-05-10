package com.sorsix.booktradingclub.repository

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long>{

    fun findAllByOwner(owner: String) : List<Book>
}