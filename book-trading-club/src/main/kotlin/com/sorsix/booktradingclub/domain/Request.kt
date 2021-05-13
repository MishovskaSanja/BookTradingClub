package com.sorsix.booktradingclub.domain

import com.sorsix.booktradingclub.domain.enumeration.RequestState
import javax.persistence.*

@Entity
class Request (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val requestId: Long,

        @ManyToOne
        val userRequesting: User,

        @ManyToOne
        val userReceiving: User,

        @ManyToMany
        val wantedBooks: List<Book>,

        @ManyToMany
        val booksToGive: List<Book>,

        @Enumerated(EnumType.STRING)
        val state: RequestState

)

