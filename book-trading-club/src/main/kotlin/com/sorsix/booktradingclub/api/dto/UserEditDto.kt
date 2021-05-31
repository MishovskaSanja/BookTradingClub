package com.sorsix.booktradingclub.api.dto

data class UserEditDto(
        val fullName: String,
        val city: String,
        val state: String,
        val address: String,
        val imgUrl: String
)