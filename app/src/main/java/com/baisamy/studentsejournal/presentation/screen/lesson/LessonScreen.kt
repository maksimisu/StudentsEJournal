package com.baisamy.studentsejournal.presentation.screen.lesson

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
import androidx.compose.runtime.mutableIntStateOf
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
import com.baisamy.studentsejournal.data.model.request.CreateTaskRequest
import com.baisamy.studentsejournal.presentation.models.LessonType
import com.baisamy.studentsejournal.presentation.models.Profile
import com.baisamy.studentsejournal.presentation.models.Task
import com.baisamy.studentsejournal.presentation.models.getLessonTypeByTitle
import com.baisamy.studentsejournal.presentation.navigation.MainNavigation
import com.baisamy.studentsejournal.presentation.ui.components.LessonTypeSelector
import com.baisamy.studentsejournal.presentation.ui.components.MainTextField
import com.baisamy.studentsejournal.presentation.ui.components.TaskListItem
import com.baisamy.studentsejournal.presentation.ui.dialogs.CreateTaskDialog
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Headline
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    var createTaskDialogue by remember { mutableStateOf(false) }

    val lesson by sharedViewModel.currentLesson.observeAsState()
    val tasks by sharedViewModel.currentLessonTasks.observeAsState()
    val course by sharedViewModel.currentCourse.observeAsState()
    val user by sharedViewModel.currentUser.observeAsState()

    var lessonName by remember { mutableStateOf(lesson!!.title) }
    var lessonType by remember { mutableStateOf(lesson!!.lessonType.getLessonTypeByTitle()) }
    var date by remember { mutableStateOf(lesson!!.date) }
    var lessonDesc by remember { mutableStateOf(lesson!!.description) }
    var studentsAttended by remember { mutableIntStateOf(lesson!!.studentsPresence.count { it.isPresent }) }
    var isTeacher by remember { mutableStateOf(course!!.teacherEmail == user!!.email) }

    // CREATE TASK DIALOGUE
    var newTaskName by remember { mutableStateOf("") }
    var newTaskOpenUntil by remember { mutableStateOf("") }
    var newTaskDesc by remember { mutableStateOf("") }
    if (createTaskDialogue) {
        CreateTaskDialog(
            onDismissRequest = { createTaskDialogue = false },
            primaryAction = {
                sharedViewModel.createTask(
                    CreateTaskRequest(
                        lessonId = lesson!!.id,
                        title = newTaskName,
                        openUntil = newTaskOpenUntil,
                        isOpen = true,
                        description = newTaskDesc
                    )
                )
                createTaskDialogue = false
            },
            taskName = newTaskName,
            onTaskNameChange = { newTaskName = it },
            openUntil = newTaskOpenUntil,
            onOpenUntilChange = { newTaskOpenUntil = it },
            taskDesc = newTaskDesc,
            onTaskDescChange = { newTaskDesc = it }
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        lessonName,
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
                            contentDescription = stringResource(id = R.string.notifications)
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
                            text = stringResource(id = R.string.lesson_name),
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        MainTextField(
                            value = lessonName,
                            onValueChange = { lessonName = it },
                            hint = stringResource(id = R.string.lesson_name)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.lesson_type),
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LessonTypeSelector(
                            currentType = lessonType,
                            onTypeChange = { lessonType = it }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.date_desc),
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        MainTextField(
                            value = date,
                            onValueChange = { date = it },
                            hint = stringResource(id = R.string.date_desc)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.desc),
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        MainTextField(
                            value = lessonDesc,
                            onValueChange = { lessonDesc = it },
                            hint = stringResource(id = R.string.desc)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    else {
                        Text(
                            text = stringResource(id = R.string.overall_info).uppercase(),
                            style = Title
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${stringResource(id = R.string.lesson_name)}: $lessonName",
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = lessonType.title,
                            style = Body,
                            color = lessonType.color
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${stringResource(id = R.string.date_desc)}: $date",
                            style = Body
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${stringResource(id = R.string.desc)}: $lessonDesc",
                            style = Body
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${stringResource(id = R.string.students_attended)} $studentsAttended",
                        style = Body
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${stringResource(id = R.string.tasks)}: ${lesson!!.tasks.size}",
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
                        Text(
                            text = stringResource(id = R.string.tasks).uppercase(),
                            style = Title
                        )
                        if (isTeacher) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = stringResource(id = R.string.create_task),
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .clickable {
                                        createTaskDialogue = true
                                    }
                                    .padding(8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    tasks!!.forEachIndexed { index, task ->
                        TaskListItem(
                            title = task.title,
                            date = task.openUntil,
                            studentsCompleted = "${task.submissions.size}/${course!!.students.size}",
                            number = index + 1,
                            isTeacher = isTeacher
                        ) {
                            sharedViewModel.setCurrentTask(task.id)
                            navHostController.navigate(MainNavigation.TaskScreen.route)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
