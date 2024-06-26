package com.baisamy.studentsejournal.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.ui.theme.Button

@Composable
fun ActionButton(
    title: String,
    modifier: Modifier = Modifier,
    width: Dp = 264.dp,
    height: Dp = 27.dp,
    actionType: ActionType = ActionType.PRIMARY,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(5.dp))
            .background(actionType.color)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = title.uppercase(),
            style = Button
        )
    }
}

@Composable
@Preview(showBackground = false)
fun ActionButtonPreview() {
    ActionButton(title = "Login") {

    }
}

@Composable
@Preview(showBackground = false)
fun ActionButtonPreview2() {
    ActionButton(title = "Register", actionType = ActionType.SECONDARY) {

    }
}

@Composable
@Preview(showBackground = false)
fun ActionButtonPreview3() {
    ActionButton(title = "Accept", actionType = ActionType.POSITIVE) {

    }
}

@Composable
@Preview(showBackground = false)
fun ActionButtonPreview4() {
    ActionButton(title = "Decline", actionType = ActionType.NEGATIVE) {

    }
}