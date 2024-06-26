package com.baisamy.studentsejournal.presentation.screen.course

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Task
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.data.model.request.CreateLessonRequest
import com.baisamy.studentsejournal.data.model.request.CreateStudentRequest
import com.baisamy.studentsejournal.data.model.request.MarkRequest
import com.baisamy.studentsejournal.data.model.response.PresenceResponse
import com.baisamy.studentsejournal.data.model.response.StudentResponse
import com.baisamy.studentsejournal.data.model.response.SubmissionResponse
import com.baisamy.studentsejournal.data.model.response.UserResponse
import com.baisamy.studentsejournal.presentation.models.LessonType
import com.baisamy.studentsejournal.presentation.models.getLessonTypeByTitle
import com.baisamy.studentsejournal.presentation.navigation.MainNavigation
import com.baisamy.studentsejournal.presentation.ui.components.CustomIconButton
import com.baisamy.studentsejournal.presentation.ui.components.LessonListItem
import com.baisamy.studentsejournal.presentation.ui.components.MarkListItem
import com.baisamy.studentsejournal.presentation.ui.components.RegisterListItem
import com.baisamy.studentsejournal.presentation.ui.components.StudentListItem
import com.baisamy.studentsejournal.presentation.ui.dialogs.AddStudentDialog
import com.baisamy.studentsejournal.presentation.ui.dialogs.CheckInDialog
import com.baisamy.studentsejournal.presentation.ui.dialogs.CreateLessonDialog
import com.baisamy.studentsejournal.presentation.ui.theme.BlackLight
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Headline
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.Red
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    // DIALOGUES STATES
    var newStudentDialogue by remember { mutableStateOf(false) }
    var newLessonDialogue by remember { mutableStateOf(false) }
    var checkInDialog by remember { mutableStateOf(false) }

    // DATA
    val course by sharedViewModel.currentCourse.observeAsState()
    val lessons by sharedViewModel.currentCourseLessons.observeAsState()
    val courseUser by sharedViewModel.currentCourseUser.observeAsState()
    val currentUser by sharedViewModel.currentUser.observeAsState()
    val students by sharedViewModel.currentCourseStudents.observeAsState()
    val submissions by sharedViewModel.currentCourseSubmissions.observeAsState()
    val currentLesson by sharedViewModel.currentLesson.observeAsState()
    val presence by sharedViewModel.currentLessonPresence.observeAsState()
    val notificationsUnread by remember { mutableStateOf(false) }
    val isTeacher by remember { mutableStateOf(currentUser!!.email == course!!.teacherEmail) }
    var selected by remember {
        if (isTeacher) {
            mutableIntStateOf(1)
        } else {
            mutableIntStateOf(2)
        }
    }

    // NEW LESSON DIALOGUE
    var newLessonName by remember { mutableStateOf("") }
    var newLessonType by remember { mutableStateOf(LessonType.LECTURE) }
    var newLessonDate by remember { mutableStateOf("") }
    var newLessonDescription by remember { mutableStateOf("") }
    if (newLessonDialogue) {
        CreateLessonDialog(
            onDismissRequest = { newLessonDialogue = false },
            primaryAction = {
                newLessonDialogue = false
                sharedViewModel.createLesson(
                    CreateLessonRequest(
                        courseToken = course!!.token,
                        title = newLessonName,
                        lessonType = newLessonType.title,
                        date = newLessonDate,
                        description = newLessonDescription
                    )
                )
                sharedViewModel.setCurrentCourse(course!!.token)
            },
            lessonName = newLessonName,
            onNameChange = { newLessonName = it },
            lessonType = newLessonType,
            onTypeChange = { newLessonType = it },
            lessonDate = newLessonDate,
            onDateChange = { newLessonDate = it },
            lessonDescription = newLessonDescription,
            onDescriptionChange = { newLessonDescription = it }
        )
    }

    // NEW STUDENT DIALOGUE
    var newStudentEmail by remember { mutableStateOf("") }
    if (newStudentDialogue) {
        AddStudentDialog(
            onDismissRequest = { newStudentDialogue = false },
            primaryAction = {
                sharedViewModel.addStudent(
                    CreateStudentRequest(
                        courseToken = course!!.token,
                        studentEmail = newStudentEmail,
                        finalMark = MarkRequest(0f, 0f)
                    )
                )
                newStudentDialogue = false
            },
            email = newStudentEmail,
            onEmailChange = { newStudentEmail = it }
        )
    }

    // CHECK IN DIALOGUE
    var newPresence by remember { mutableStateOf(presence) }
    if (checkInDialog) {
        CheckInDialog(
            presence = newPresence ?: listOf(),
            presenceChange = { newPresence = it },
            students = students!!,
            onDismissRequest = {
                checkInDialog = false
                newPresence = presence
            },
            onConfirmClick = {
                checkInDialog = false
            }
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
                        course!!.title,
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
                    Icon(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clickable {
                                navHostController.navigate(MainNavigation.CourseNotificationsScreen.route)
                            }
                            .padding(8.dp),
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = stringResource(id = R.string.notifications),
                        tint = if (notificationsUnread) {
                            Red
                        } else {
                            White
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // OVERALL INFO

            var isCardHiddenState by remember { mutableStateOf(false) }
            var tasks = 0
            course!!.lessons.forEach { lesson ->
                lesson.tasks.forEach { _ ->
                    tasks++
                }
            }
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
                    if (!isCardHiddenState) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(id = R.string.overall_info).uppercase(),
                                style = Title
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_more_up),
                                contentDescription = stringResource(id = R.string.more),
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .clickable {
                                        isCardHiddenState = !isCardHiddenState
                                    }
                                    .padding(8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = "${stringResource(id = R.string.teacher)}: ${courseUser?.name}",
                                style = Body
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "${stringResource(id = R.string.students)}: ${course!!.students.size}",
                                style = Body
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "${stringResource(id = R.string.lessons)}: ${course!!.lessons.size}",
                                style = Body
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "${stringResource(id = R.string.tasks)}: $tasks",
                                style = Body
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(id = R.string.overall_info).uppercase(),
                                style = Title
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_more_down),
                                contentDescription = stringResource(id = R.string.less),
                                modifier = Modifier
                                    .clickable {
                                        isCardHiddenState = !isCardHiddenState
                                    }
                            )
                        }
                    }
                }
            }

            // MENU

            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (isTeacher) {
                    item {
                        CustomIconButton(
                            icon = Icons.Outlined.PeopleOutline,
                            title = stringResource(id = R.string.students),
                            tint = if (selected == 1) {
                                LightBlue
                            } else {
                                BlackLight
                            },
                            action = {
                                selected = 1
                            }
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(12.dp))
                }
                item {
                    CustomIconButton(
                        icon = Icons.Outlined.Article,
                        title = stringResource(id = R.string.lessons),
                        tint = if (selected == 2) {
                            LightBlue
                        } else {
                            BlackLight
                        },
                        action = {
                            selected = 2
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.width(12.dp))
                }
                item {
                    CustomIconButton(
                        icon = Icons.Outlined.Task,
                        title = stringResource(id = R.string.mark),
                        tint = if (selected == 3) {
                            LightBlue
                        } else {
                            BlackLight
                        },
                        action = {
                            selected = 3
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.width(12.dp))
                }
                item {
                    CustomIconButton(
                        icon = Icons.Outlined.FactCheck,
                        title = stringResource(id = R.string.register),
                        tint = if (selected == 4) {
                            LightBlue
                        } else {
                            BlackLight
                        },
                        action = {
                            selected = 4
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            when (selected) {
                1 -> {

                    // STUDENTS

                    Surface(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxSize(),
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
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.students).uppercase(),
                                    style = Title
                                )
                                if (isTeacher) {
                                    Icon(
                                        imageVector = Icons.Outlined.Add,
                                        contentDescription = stringResource(id = R.string.add_student),
                                        modifier = Modifier
                                            .clip(shape = CircleShape)
                                            .clickable {
                                                newStudentDialogue = true
                                            }
                                            .padding(8.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                students!!.forEachIndexed { index, profile ->
                                    item {
                                        StudentListItem(name = profile.name, number = index + 1) {
                                            sharedViewModel.setCurrentStudent(course!!.students.find { it.studentEmail == profile.email }!!.id)
                                            navHostController.navigate(MainNavigation.StudentScreen.route)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                2 -> {

                    // LESSONS

                    Surface(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxSize(),
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
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.lessons).uppercase(),
                                    style = Title
                                )
                                if (isTeacher) {
                                    Icon(
                                        imageVector = Icons.Outlined.Add,
                                        contentDescription = stringResource(id = R.string.add_student),
                                        modifier = Modifier
                                            .clip(shape = CircleShape)
                                            .clickable {
                                                newLessonDialogue = true
                                            }
                                            .padding(8.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                lessons!!.forEachIndexed { index, lesson ->
                                    item {
                                        LessonListItem(
                                            title = lesson.title,
                                            type = lesson.lessonType.getLessonTypeByTitle(),
                                            date = lesson.date,
                                            number = index + 1,
                                            onClick = {
                                                sharedViewModel.setCurrentLesson(lesson.id)
                                                navHostController.navigate(MainNavigation.LessonScreen.route)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                3 -> {

                    // MARKS

                    Surface(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxSize(),
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
                                text = stringResource(id = R.string.lessons).uppercase(),
                                style = Title
                            )
                            if (!isTeacher) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "${stringResource(id = R.string.average_rate)} 54",
                                    style = Body
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "${stringResource(id = R.string.final_mark)} 0",
                                    style = Body
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val marks =
                                    mutableListOf<Triple<UserResponse, StudentResponse, List<SubmissionResponse>>>()
                                students!!.forEach { user ->
                                    val subs =
                                        submissions!!.filter { it.studentEmail == user.email }
                                    marks.add(
                                        Triple(
                                            user,
                                            course!!.students.find { it.studentEmail == user.email }!!,
                                            subs
                                        )
                                    )
                                }
                                marks.forEachIndexed { i, it ->
                                    item {
                                        MarkListItem(
                                            mark = it,
                                            number = i + 1,
                                            isTeacher = isTeacher
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                4 -> {

                    // REGISTER

                    Surface(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxSize(),
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
                                text = stringResource(id = R.string.register).uppercase(),
                                style = Title
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                course!!.lessons.forEachIndexed { index, lesson ->
                                    item {
                                        RegisterListItem(
                                            title = lesson.title,
                                            present = lesson.studentsPresence.count { it.isPresent },
                                            maximum = lesson.studentsPresence.size,
                                            date = lesson.date,
                                            number = index + 1
                                        ) {
                                            sharedViewModel.setCurrentLesson(lesson.id)
                                            checkInDialog = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}