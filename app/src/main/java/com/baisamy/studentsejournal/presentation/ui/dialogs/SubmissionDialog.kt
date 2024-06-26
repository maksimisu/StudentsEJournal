package com.baisamy.studentsejournal.presentation.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.ui.components.ActionButton
import com.baisamy.studentsejournal.presentation.ui.components.MainTextField
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White

@Composable
fun SubmissionDialog(
    onDismissRequest: () -> Unit,
    primaryAction: () -> Unit
) {
    var mark by remember { mutableStateOf("") }
    var maxMark by remember { mutableStateOf("") }
    var notesList by remember {
        mutableStateOf(
            mutableListOf<String>("some note", "some note")
        )
    }
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
                    text = stringResource(id = R.string.mark),
                    style = Title
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    MainTextField(
                        value = mark,
                        onValueChange = { mark = it },
                        hint = stringResource(id = R.string.mark),
                        modifier = Modifier.width(64.dp),
                        isMaxWidth = false
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "/",
                        style = Title
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    MainTextField(
                        value = maxMark,
                        onValueChange = { maxMark = it },
                        hint = stringResource(id = R.string.mark),
                        modifier = Modifier.width(64.dp),
                        isMaxWidth = false
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.notes),
                    style = Title
                )
                Spacer(modifier = Modifier.height(16.dp))
                notesList.forEachIndexed { index, s ->
                    MainTextField(
                        value = s,
                        onValueChange = { value ->
                            notesList[index] = value
                        },
                        hint = ""
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                ActionButton(
                    title = stringResource(id = R.string.new_note),
                    actionType = ActionType.SECONDARY
                ) {
                    notesList.add("New note")
                }
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
fun SubmissionDialogPreview() {
    SubmissionDialog(
        onDismissRequest = { /*TODO*/ },
        primaryAction = { /*TODO*/ }
    )
}