package com.baisamy.studentsejournal.presentation.models

import com.baisamy.studentsejournal.presentation.models.NotificationType

data class Notification(
    val id: Int,
    val courseTitle: String,
    val inviter: String,
    val notificationType: NotificationType,
    val date: String
)
