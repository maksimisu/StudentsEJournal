package com.baisamy.studentsejournal.data.model.request

data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String
)
