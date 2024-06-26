package com.baisamy.studentsejournal.data.model.response

data class TaskResponse(
    val id: Long,
    val lessonId: Long,
    val title: String,
    val openUntil: String,
    val isOpen: Boolean,
    val description: String,
    val submissions: List<SubmissionResponse>
)
