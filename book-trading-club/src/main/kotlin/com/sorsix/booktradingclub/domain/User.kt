package com.sorsix.booktradingclub.domain

import lombok.Data
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
@Data
data class User (
        @Id
        val username: String,
        val password: String,

        var fullName: String,
        var city: String,
        var state: String,
        var address: String,

){

}

