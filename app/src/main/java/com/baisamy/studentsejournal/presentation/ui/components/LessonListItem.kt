package com.baisamy.studentsejournal.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.models.LessonType
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonListItem(
    title: String,
    type: LessonType,
    date: String,
    number: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = {
            onClick()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = Title
                )
                Text(
                    text = number.toString(),
                    style = Title
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = type.title,
                style = Body,
                color = type.color
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${stringResource(id = R.string.date)} $date",
                style = Body
            )
        }
    }
}