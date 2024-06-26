package com.baisamy.studentsejournal.presentation.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.data.model.response.LessonResponse
import com.baisamy.studentsejournal.data.model.response.PresenceResponse
import com.baisamy.studentsejournal.data.model.response.UserResponse
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.ui.components.ActionButton
import com.baisamy.studentsejournal.presentation.ui.theme.Green
import com.baisamy.studentsejournal.presentation.ui.theme.Red
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray

@Composable
fun CheckInDialog(
    students: List<UserResponse>,
    presence: List<PresenceResponse>,
    presenceChange: (List<PresenceResponse>) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit
) {

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
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
                presence.forEachIndexed { index, it ->
                    Card(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(4.dp))
                                .background(WhiteGray)
                                .clickable {
                                    presence[index].isPresent = !it.isPresent
                                    presenceChange(presence)
                                }
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Text(
                                    text = "${index + 1}",
                                    style = Title
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${students.find { student -> student.email == it.studentEmail }?.name}",
                                    style = Title
                                )
                            }
                            Surface(
                                modifier = Modifier
                                    .size(12.dp),
                                color = if (it.isPresent) {
                                    Green
                                } else {
                                    Red
                                },
                                shape = CircleShape,
                                content = {}
                            )
                        }
                    }
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
                    title = stringResource(id = R.string.confirm),
                    actionType = ActionType.PRIMARY
                ) {
                    onConfirmClick()
                }
            }
        }
    }
}