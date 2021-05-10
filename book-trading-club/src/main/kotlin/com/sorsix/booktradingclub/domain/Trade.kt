package com.sorsix.booktradingclub.domain

data class Trade (
        val user1: User,
        val user2: User,
        val book1: Book,
        val book2: Book
){
}