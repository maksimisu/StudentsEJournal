package com.baisamy.studentsejournal.data.model.request

data class CreateLessonRequest(
    val courseToken: String,
    val title: String,
    val lessonType: String,
    val date: String,
    val description: String
)
