package com.sorsix.booktradingclub.domain

import com.sorsix.booktradingclub.domain.enumeration.BookStatus
import javax.persistence.*

@Entity
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var name: String,
        var description: String,
        var imgUrl: String,
        @ManyToOne
        val owner: User,
        @Enumerated(EnumType.STRING)
        var status: BookStatus
)