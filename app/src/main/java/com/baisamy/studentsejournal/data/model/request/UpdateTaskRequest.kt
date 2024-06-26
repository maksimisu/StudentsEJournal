package com.baisamy.studentsejournal.data.model.request

data class UpdateTaskRequest(
    val id: Long,
    val title: String,
    val openUntil: String,
    val isOpen: Boolean,
    val description: String
)
