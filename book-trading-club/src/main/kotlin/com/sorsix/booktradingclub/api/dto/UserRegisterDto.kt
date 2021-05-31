package com.sorsix.booktradingclub.api.dto

data class UserRegisterDto(
        val username: String,
        val password: String,
        val fullName: String,
        val city: String,
        val state: String,
        val address: String,
        val imgUrl: String
)