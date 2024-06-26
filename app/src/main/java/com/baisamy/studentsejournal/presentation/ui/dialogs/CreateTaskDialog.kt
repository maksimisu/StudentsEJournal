package com.baisamy.studentsejournal.presentation.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.ui.components.ActionButton
import com.baisamy.studentsejournal.presentation.ui.components.MainTextField
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White

@Composable
fun CreateTaskDialog(
    onDismissRequest: () -> Unit,
    primaryAction: () -> Unit,
    taskName: String,
    onTaskNameChange: (String) -> Unit,
    openUntil: String,
    onOpenUntilChange: (String) -> Unit,
    taskDesc: String,
    onTaskDescChange: (String) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .width(296.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                contentColor = White,
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(state = rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(id = R.string.create_task),
                    style = Title
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.task_name),
                    style = Body
                )
                Spacer(modifier = Modifier.height(8.dp))
                MainTextField(
                    value = taskName,
                    onValueChange = { onTaskNameChange(it) },
                    hint = stringResource(id = R.string.task_name)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.open_until),
                    style = Body
                )
                Spacer(modifier = Modifier.height(8.dp))
                MainTextField(
                    value = openUntil,
                    onValueChange = { onOpenUntilChange(it) },
                    hint = stringResource(id = R.string.date_desc)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.desc),
                    style = Body
                )
                Spacer(modifier = Modifier.height(8.dp))
                MainTextField(
                    value = taskDesc,
                    onValueChange = { onTaskDescChange(it) },
                    hint = stringResource(id = R.string.desc)
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