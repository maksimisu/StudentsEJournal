package com.baisamy.studentsejournal.presentation.ui.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.baisamy.studentsejournal.presentation.ui.components.MainTextField
import com.baisamy.studentsejournal.presentation.ui.theme.BlackLight
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White

@Composable
fun CreateClassDialog(
    onDismissRequest: () -> Unit,
    primaryAction: () -> Unit,
    name: String,
    onNameChange: (String) -> Unit
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
                    text = stringResource(id = R.string.create_class),
                    style = Title
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.class_name),
                    style = Body
                )
                Spacer(modifier = Modifier.height(8.dp))
                MainTextField(
                    value = name,
                    onValueChange = { onNameChange(it) },
                    hint = stringResource(id = R.string.class_name)
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
                    title = stringResource(id = R.string.create),
                    actionType = ActionType.PRIMARY
                ) {
                    primaryAction()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun CreateClassDialogPreview() {
    var token = ""
    CreateClassDialog(
        onDismissRequest = { /*TODO*/ },
        primaryAction = { /*TODO*/ },
        name = token,
        onNameChange = { token = it }
    )
}