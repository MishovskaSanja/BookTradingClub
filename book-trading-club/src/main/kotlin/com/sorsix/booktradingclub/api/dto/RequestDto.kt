package com.sorsix.booktradingclub.api.dto

import com.sorsix.booktradingclub.domain.Book
import com.sorsix.booktradingclub.domain.enumeration.RequestState
import com.sorsix.booktradingclub.domain.User


data class RequestDto(
        val userRequesting: User,
        val userReceiving: User,
        val wantedBooks: List<Book>,
        val booksToGive: List<Book>,
        val state: RequestState
)
