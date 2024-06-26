package com.baisamy.studentsejournal.presentation.screen.task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.navigation.MainNavigation
import com.baisamy.studentsejournal.presentation.ui.components.MainTextField
import com.baisamy.studentsejournal.presentation.ui.components.SubmissionListItem
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Green
import com.baisamy.studentsejournal.presentation.ui.theme.Headline
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.Red
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val task by sharedViewModel.currentTask.observeAsState()
    val course by sharedViewModel.currentCourse.observeAsState()
    val user by sharedViewModel.currentUser.observeAsState()
    val students by sharedViewModel.currentCourseStudents.observeAsState()

    var taskName by remember { mutableStateOf(task!!.title) }
    var closingDate by remember { mutableStateOf(task!!.openUntil) }
    var taskDescription by remember { mutableStateOf(task!!.description) }
    var isOpen by remember { mutableStateOf(task!!.isOpen) }
    var studentsCompleted by remember { mutableStateOf(task!!.submissions.size) }
    var isTeacher by remember { mutableStateOf(course!!.teacherEmail == user!!.email) }
    var myMark by remember { mutableStateOf(task!!.submissions.find { it.studentEmail == user!!.email }?.mark) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        taskName,
                        style = Headline
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightBlue,
                    titleContentColor = White,
                    actionIconContentColor = White,
                    navigationIconContentColor = White
                ),
                actions = {
                    if (isTeacher) {
                        Icon(
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .clickable {
                                    TODO("Save changes")
                                }
                                .padding(8.dp),
                            imageVector = Icons.Outlined.Save,
                            contentDescription = stringResource(id = R.string.save)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                },
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
        Column(
            modifier = Modifier
                .padding(top = topPadding + 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
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
                    if (isTeacher) {
                        Text(
                            text = stringResource(id = R.string.overall_info).uppercase(),
                            style = Title
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.task_name),
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        MainTextField(
                            value = taskName,
                            onValueChange = { taskName = it },
                            hint = stringResource(id = R.string.task_name)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.open_until),
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        MainTextField(
                            value = closingDate,
                            onValueChange = { closingDate = it },
                            hint = stringResource(id = R.string.date_desc)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.desc),
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        MainTextField(
                            value = taskDescription,
                            onValueChange = { taskDescription = it },
                            hint = stringResource(id = R.string.desc)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "${stringResource(id = R.string.open)}:",
                                style = Body
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Checkbox(
                                checked = isOpen,
                                onCheckedChange = { isOpen = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Green,
                                    uncheckedColor = Red,
                                    checkmarkColor = White
                                )
                            )
                        }
                    }
                    else {
                        Text(
                            text = stringResource(id = R.string.overall_info).uppercase(),
                            style = Title
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${stringResource(id = R.string.task_name)}: $taskName",
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${stringResource(id = R.string.open_until)}: $closingDate",
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${stringResource(id = R.string.desc)}: $taskDescription",
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${stringResource(id = R.string.open)}: $isOpen",
                            style = Body
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${stringResource(id = R.string.students_completed)} $studentsCompleted",
                        style = Body
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        if (isTeacher) {
                            Text(
                                text = stringResource(id = R.string.submissions).uppercase(),
                                style = Title
                            )
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = stringResource(id = R.string.add_submission),
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .clickable {
                                        // TODO
                                    }
                                    .padding(8.dp)
                            )
                        } else {
                            Text(
                                text = stringResource(id = R.string.my_submission).uppercase(),
                                style = Title
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if (isTeacher) {
                        task!!.submissions.forEach { submission ->
                            SubmissionListItem(
                                studentName = students!!.find { it.email == submission.studentEmail }!!.name,
                                number = 1,
                                mark = "${submission.mark.rating}/${submission.mark.maxRating}"
                            ) {
                                sharedViewModel.setCurrentSubmission(submission.id)
                            }
                        }

                    } else {
                        Text(
                            text = "${stringResource(id = R.string.mark)}: ${myMark?.rating}/${myMark?.maxRating}",
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${stringResource(id = R.string.notes)}:",
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        task!!.submissions.find { it.studentEmail == user!!.email }?.notes?.forEach {
                            Text(
                                text = "— ${it.note}",
                                style = Body,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}