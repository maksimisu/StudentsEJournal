package com.baisamy.studentsejournal.data.model.request

data class UpdatePresenceRequest(
    val id: Long,
    val isPresent: Boolean
)
