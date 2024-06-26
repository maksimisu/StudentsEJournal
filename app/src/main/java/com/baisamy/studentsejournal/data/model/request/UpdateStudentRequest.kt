package com.baisamy.studentsejournal.data.model.request

data class UpdateStudentRequest(
    val id: Long,
    val courseToken: String,
    val studentEmail: String,
    val finalMark: MarkRequest
)
