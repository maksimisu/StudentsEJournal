package com.baisamy.studentsejournal.presentation.models

enum class NotificationType(
    val title: String,
    val message: String
) {
    INVITATION_REQUEST(
        title = "Invitation to class",
        message = "You are invited to class."
    ),
    NEW_MESSAGE(
        title = "New message",
        message = "You have new message."
    ),
    NEW_MARK(
        title = "New mark",
        message = "You have new mark."
    ),
    UPCOMING_EVENT(
        title = "Upcoming event",
        message = "There is an upcoming event."
    ),
    JOIN_REQUEST(
        title = "Join request",
        message = "There is new join request."
    )
}
