package com.baisamy.studentsejournal.data.model.request

data class UpdateCourseRequest(
    val token: String,
    val title: String,
    val isRunning: Boolean
)
