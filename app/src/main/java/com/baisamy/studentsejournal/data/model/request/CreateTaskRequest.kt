package com.baisamy.studentsejournal.data.model.request

data class CreateTaskRequest(
    val lessonId: Long,
    val title: String,
    val openUntil: String,
    val isOpen: Boolean,
    val description: String
)
