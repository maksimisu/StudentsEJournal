package com.baisamy.studentsejournal.data.model.request

data class CreateSubmissionRequest(
    val studentEmail: String,
    val taskId: Long,
    val mark: MarkRequest,
    val submissionDate: String
)
