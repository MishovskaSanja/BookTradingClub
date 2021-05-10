package com.sorsix.booktradingclub.domain

import javax.persistence.*

@Entity
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val name:String,
        val description:String,

        val owner: String
)