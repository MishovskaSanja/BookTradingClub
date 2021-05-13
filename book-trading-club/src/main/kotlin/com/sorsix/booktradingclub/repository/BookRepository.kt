package com.sorsix.booktradingclub.repository

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface BookRepository : JpaRepository<Book, Long>{

    fun findAllByOwner(owner: User) : List<Book>

    @Modifying
    @Transactional
    @Query("update Book b set  b.name = :name, b.description = :description where b.id = :id")
    fun editBook(id: Long, name: String, description: String): Int

}