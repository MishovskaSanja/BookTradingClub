package com.sorsix.booktradingclub.api.dto

import javax.management.monitor.StringMonitor

data class UserDto (
    val fullName: String,
    val city: String,
    val state: String,
    val address: String
){}