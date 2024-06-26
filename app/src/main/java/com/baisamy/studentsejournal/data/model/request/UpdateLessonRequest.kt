package com.baisamy.studentsejournal.data.model.request

data class UpdateLessonRequest(
    val id: Long,
    val title: String,
    val lessonType: String,
    val date: String,
    val description: String
)
