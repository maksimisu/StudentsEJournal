package com.baisamy.studentsejournal.presentation.screen.student


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.ui.components.ActionButton
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Headline
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val student by sharedViewModel.currentStudent.observeAsState()
    val studentUser by sharedViewModel.currentStudentUser.observeAsState()
    val course by sharedViewModel.currentCourse.observeAsState()
    val average by sharedViewModel.currentStudentAverageRate.observeAsState()
    var lessonsPresent = 0
    course!!.lessons.forEach { lesson ->
        lessonsPresent += lesson.studentsPresence.count {
            student!!.studentEmail == it.studentEmail && it.isPresent
        }
    }
    var tasksCompleted = 0
    var tasks = 0
    course!!.lessons.forEach {  lesson ->
        lesson.tasks.forEach { task ->
            tasks++
            task.submissions.forEach {
                if (it.studentEmail == student!!.studentEmail)
                    tasksCompleted++
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.student).uppercase(),
                        style = Headline
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightBlue,
                    titleContentColor = White,
                    actionIconContentColor = White,
                    navigationIconContentColor = White
                ),
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 16.dp)
                            .clip(shape = CircleShape)
                            .clickable {
                                navHostController.popBackStack()
                            }
                            .padding(8.dp),
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            )
        }
    ) { paddingValues ->
        val topPadding = paddingValues.calculateTopPadding()
        Box(
            modifier = Modifier
                .padding(top = topPadding + 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                shadowElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(WhiteGray)
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.overall_info).uppercase(),
                        style = Title
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${stringResource(id = R.string.name)}: ${studentUser?.name}",
                        style = Body
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${stringResource(id = R.string.lessons)}: ${lessonsPresent}/${course?.lessons?.size}",
                        style = Body
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${stringResource(id = R.string.tasks)}: ${tasksCompleted}/${tasks}",
                        style = Body
                    )
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
                            text = "${stringResource(id = R.string.final_mark)} ${student?.finalMark?.rating}/${student?.finalMark?.maxRating}",
                            style = Body
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = stringResource(id = R.string.final_mark)
                        )
                    }
                }
            }
            ActionButton(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.BottomCenter),
                title = stringResource(id = R.string.delete_from_class),
                actionType = ActionType.NEGATIVE
            ) {
                TODO("Delete from class.")
            }
        }
    }
}