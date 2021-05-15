package com.sorsix.booktradingclub.domain

import com.sorsix.booktradingclub.domain.enumeration.RequestStatus
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

        @ManyToOne
        val wantedBook: Book,

        @ManyToOne
        val bookToGive: Book,

        @Enumerated(EnumType.STRING)
        val status: RequestStatus
)

