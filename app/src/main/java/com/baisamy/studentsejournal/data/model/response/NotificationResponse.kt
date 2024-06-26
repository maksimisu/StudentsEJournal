package com.baisamy.studentsejournal.data.model.response

import com.baisamy.studentsejournal.presentation.models.NotificationType

data class NotificationResponse(
    val id: Long,
    val courseToken: String,
    val userEmail: String,
    val notificationType: NotificationType
)
