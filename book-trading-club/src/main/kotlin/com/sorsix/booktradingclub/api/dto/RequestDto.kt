package com.sorsix.booktradingclub.api.dto

data class RequestDto(
        val wantedBooks: List<Long>,
        val booksToGive: List<Long>
)
