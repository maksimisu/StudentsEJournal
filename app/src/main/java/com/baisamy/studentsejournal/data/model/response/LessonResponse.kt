package com.baisamy.studentsejournal.data.model.response

data class LessonResponse(
    val id: Long,
    val courseToken: String,
    val title: String,
    val lessonType: String,
    val date: String,
    val description: String,
    var studentsPresence: List<PresenceResponse>,
    var tasks: List<TaskResponse>
)
