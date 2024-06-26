package com.baisamy.studentsejournal.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.models.NotificationType
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.models.Notification
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray

@Composable
fun Notification(
    notification: Notification,
    positiveAction: () -> Unit,
    negativeAction: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .background(WhiteGray)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = notification.notificationType.title.uppercase(),
                style = Title
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = notification.notificationType.message,
                style = Body
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${stringResource(id = R.string._class)} ${notification.courseTitle}",
                style = Body
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${stringResource(id = R.string.inviter)} ${notification.inviter}",
                style = Body
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${stringResource(id = R.string.date)} ${notification.date}",
                style = Body
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (notification.notificationType == NotificationType.INVITATION_REQUEST ||
                notification.notificationType == NotificationType.JOIN_REQUEST
            ) {
                ActionButton(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    title = stringResource(id = R.string.decline),
                    actionType = ActionType.NEGATIVE,
                    onClick = {
                        negativeAction()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ActionButton(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    title = stringResource(id = R.string.accept),
                    actionType = ActionType.POSITIVE,
                    onClick = {
                        positiveAction()
                    }
                )
            } else {
                ActionButton(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    title = stringResource(id = R.string.accept),
                    actionType = ActionType.POSITIVE,
                    onClick = {
                        positiveAction()
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun NotificationPreview() {
    Notification(
        notification = Notification(
            id = 1,
            courseTitle = "Android",
            inviter = "Vasyl Petrovych",
            notificationType = NotificationType.JOIN_REQUEST,
            date = "10 October 2024"
        ),
        positiveAction = {  },
        negativeAction = {  })
}

@Composable
@Preview(showBackground = false)
fun Notification2Preview() {
    Notification(
        notification = Notification(
            id = 1,
            courseTitle = "Android",
            inviter = "Vasyl Petrovych",
            notificationType = NotificationType.NEW_MESSAGE,
            date = "10 October 2024"
        ),
        positiveAction = {  },
        negativeAction = {  })
}