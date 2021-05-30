package com.sorsix.booktradingclub.domain

import lombok.Data
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        val username: String,
        val password: String,
        var fullName: String,
        var city: String,
        var state: String,
        var address: String,
        var imgUrl: String
)

