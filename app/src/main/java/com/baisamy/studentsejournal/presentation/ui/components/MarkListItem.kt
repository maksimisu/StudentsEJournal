package com.baisamy.studentsejournal.presentation.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.data.model.response.StudentResponse
import com.baisamy.studentsejournal.data.model.response.SubmissionResponse
import com.baisamy.studentsejournal.data.model.response.UserResponse
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White

@Composable
fun MarkListItem(
    mark: Triple<UserResponse, StudentResponse, List<SubmissionResponse>>,
    isTeacher: Boolean = true,
    number: Int
) {
    var sum = 0f
    var average = 0f
    mark.third.forEach {
        if (it.mark.rating != null && it.mark.maxRating != null)
            sum += it.mark.rating / it.mark.maxRating * 100
    }
    if (mark.third.isNotEmpty())
        average = sum / mark.third.size
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(8.dp)
        ) {
            if (isTeacher) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = mark.first.name,
                        style = Title
                    )
                    Text(
                        text = number.toString(),
                        style = Title
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${stringResource(id = R.string.average_rate)} $average",
                    style = Body
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${stringResource(id = R.string.final_mark)} ${mark.second.finalMark?.rating}/${mark.second.finalMark?.maxRating}",
                        style = Body
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = stringResource(id = R.string.final_mark)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    mark.third.forEach {
                        Text(
                            text = "${it.mark.rating}/${it.mark.maxRating}",
                            style = Body
                        )
                    }
                }
            } else {
                /*Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = marksList[number].first,
                        style = Title
                    )
                    Text(
                        text = number.toString(),
                        style = Title
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${marksList[number].second}/100",
                    style = Body
                )*/
            }
        }
    }
}