package com.baisamy.studentsejournal.data.model.request

import com.baisamy.studentsejournal.presentation.models.NotificationType

data class CreateNotificationRequest(
    val courseToken: String,
    val userEmail: String,
    val notificationType: NotificationType
)
