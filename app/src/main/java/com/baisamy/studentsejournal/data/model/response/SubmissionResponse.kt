package com.baisamy.studentsejournal.data.model.response

data class SubmissionResponse(
    val id: Long,
    val studentEmail: String,
    val taskId: Long,
    val mark: MarkResponse,
    val submissionDate: String,
    val notes: List<NoteResponse>
)
