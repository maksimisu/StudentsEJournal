package com.baisamy.studentsejournal.presentation.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.ui.components.ActionButton
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White

@Composable
fun DeleteDialog(
    onDismissRequest: () -> Unit,
    negativeAction: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .width(296.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                contentColor = White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(id = R.string.delete),
                    style = Title
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.cant_be_reversed),
                    style = Body
                )
                Spacer(modifier = Modifier.height(16.dp))
                ActionButton(
                    title = stringResource(id = R.string.cancel),
                    actionType = ActionType.SECONDARY
                ) {
                    onDismissRequest()
                }
                Spacer(modifier = Modifier.height(16.dp))
                ActionButton(
                    title = stringResource(id = R.string.delete),
                    actionType = ActionType.NEGATIVE
                ) {
                    negativeAction()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun DeleteDialogPreview() {
    DeleteDialog(
        onDismissRequest = { /*TODO*/ },
        negativeAction = { /*TODO*/ }
    )
}