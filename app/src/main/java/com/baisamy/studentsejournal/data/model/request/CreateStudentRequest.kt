package com.baisamy.studentsejournal.data.model.request

data class CreateStudentRequest(
    val courseToken: String,
    val studentEmail: String,
    val finalMark: MarkRequest
)
