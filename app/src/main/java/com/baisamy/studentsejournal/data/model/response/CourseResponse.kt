package com.baisamy.studentsejournal.data.model.response

data class CourseResponse(
    val token: String,
    val title: String,
    val teacherEmail: String,
    val isRunning: Boolean,
    var students: List<StudentResponse>,
    var lessons: List<LessonResponse>
)
