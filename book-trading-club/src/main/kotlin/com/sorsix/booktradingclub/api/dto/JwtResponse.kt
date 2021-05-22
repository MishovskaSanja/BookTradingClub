package com.sorsix.booktradingclub.api.dto

data class JwtResponse(
        val accessToken: String,
        val username: String
)