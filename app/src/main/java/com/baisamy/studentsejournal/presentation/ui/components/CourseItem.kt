package com.baisamy.studentsejournal.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.data.model.response.CourseResponse
import com.baisamy.studentsejournal.presentation.ui.theme.BlackLight
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Green
import com.baisamy.studentsejournal.presentation.ui.theme.Red
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray

@Composable
fun CourseItem(
    email: String,
    course: CourseResponse,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick()
                }
                .background(WhiteGray)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth(0.75f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = course.title,
                    style = Title
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${stringResource(R.string.teacher)}: ${course.teacherEmail}",
                    style = Body
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${stringResource(R.string.role)} ${
                        if (course.teacherEmail == email) stringResource(id = R.string.teacher)
                        else stringResource(id = R.string.student)
                    }",
                    style = Body
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxWidth(0.25f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Row {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        imageVector = Icons.Outlined.Person,
                        contentDescription = stringResource(id = R.string.students_quantity),
                        tint = BlackLight
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Surface(
                        modifier = Modifier
                            .size(24.dp),
                        color = BlackLight,
                        shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${course.students.size}",
                                style = Body,
                                color = WhiteGray
                            )
                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .size(12.dp),
                    color = if (course.isRunning) {
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
}