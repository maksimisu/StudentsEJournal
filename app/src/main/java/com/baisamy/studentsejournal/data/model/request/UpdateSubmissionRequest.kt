package com.baisamy.studentsejournal.data.model.request

data class UpdateSubmissionRequest(
    val id: Long,
    val studentEmail: String,
    val taskId: Long,
    val mark: MarkRequest,
    val submissionDate: String
)
