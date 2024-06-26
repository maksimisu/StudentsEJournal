package com.baisamy.studentsejournal.data.model.request

data class CreateNoteRequest(
    val submissionId: Long,
    val note: String
)
