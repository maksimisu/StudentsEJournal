package com.baisamy.studentsejournal.data.model.response

data class PresenceResponse(
    val id: Long,
    val lessonId: Long,
    val studentEmail: String,
    var isPresent: Boolean
)
