package com.sorsix.booktradingclub.api.dto

import com.sorsix.booktradingclub.domain.enumeration.BookStatus

data class BookDto (
        val name: String,
        val description: String,
        val status: BookStatus
)