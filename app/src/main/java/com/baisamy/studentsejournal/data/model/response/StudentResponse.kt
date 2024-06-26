package com.baisamy.studentsejournal.data.model.response

data class StudentResponse(
    val id: Long,
    val courseToken: String,
    val studentEmail: String,
    val finalMark: MarkResponse?
)
