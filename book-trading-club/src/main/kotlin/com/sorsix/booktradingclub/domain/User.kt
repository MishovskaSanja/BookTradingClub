package com.sorsix.booktradingclub.domain


data class User (
    val fullName: String,
    val city: String,
    val state: String,
    val requests: List<Request>

){

}

