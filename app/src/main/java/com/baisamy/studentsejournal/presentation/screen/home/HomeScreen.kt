package com.baisamy.studentsejournal.presentation.screen.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.navigation.MainNavigation
import com.baisamy.studentsejournal.presentation.ui.components.CourseItem
import com.baisamy.studentsejournal.presentation.ui.dialogs.CreateClassDialog
import com.baisamy.studentsejournal.presentation.ui.dialogs.JoinClassDialog
import com.baisamy.studentsejournal.presentation.ui.dialogs.NewClassDialog
import com.baisamy.studentsejournal.presentation.ui.theme.BlackLight
import com.baisamy.studentsejournal.presentation.ui.theme.Headline
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.Red
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    // DIALOGUES STATES
    var newCourseDialogue by remember { mutableStateOf(false) }
    var joinCourseDialogue by remember { mutableStateOf(false) }
    var createCourseDialogue by remember { mutableStateOf(false) }

    // DATA
    val courses by sharedViewModel.courses.observeAsState()
    val user by sharedViewModel.currentUser.observeAsState()
    val notificationsUnreadState by remember { mutableStateOf(false) }

    // FOR JOIN DIALOGUE
    var joinToken by remember { mutableStateOf("") }

    // FOR CREATE DIALOGUE
    var createCourseName by remember { mutableStateOf("") }

    if (newCourseDialogue) {
        NewClassDialog(
            onDismissRequest = { newCourseDialogue = false },
            joinAction = {
                newCourseDialogue = false
                joinCourseDialogue = true
            },
            createAction = {
                newCourseDialogue = false
                createCourseDialogue = true
            }
        )
    }

    if (joinCourseDialogue) {
        JoinClassDialog(
            onDismissRequest = { joinCourseDialogue = false },
            primaryAction = { /*TODO*/ },
            token = joinToken,
            onTokenChange = { joinToken = it }
        )
    }

    if (createCourseDialogue) {
        CreateClassDialog(
            onDismissRequest = { createCourseDialogue = false },
            primaryAction = {
                createCourseDialogue = false
                sharedViewModel.createCourse(createCourseName)
            },
            name = createCourseName,
            onNameChange = { createCourseName = it }
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
                        stringResource(id = R.string.classes).uppercase(),
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
                                navHostController.navigate(MainNavigation.UserNotificationsScreen.route)
                            }
                            .padding(8.dp),
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = stringResource(id = R.string.notifications),
                        tint = if (notificationsUnreadState) {
                            Red
                        } else {
                            White
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clickable {
                                navHostController.navigate(MainNavigation.ProfileScreen.route)
                            }
                            .padding(8.dp),
                        imageVector = Icons.Outlined.Person,
                        contentDescription = stringResource(id = R.string.profile)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    newCourseDialogue = true
                },
                shape = CircleShape,
                containerColor = LightBlue,
                contentColor = White
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(id = R.string.new_class)
                )
            }
        }
    ) { paddingValues ->
        val topPadding = paddingValues.calculateTopPadding()
        Box(
            modifier = Modifier
                .padding(top = topPadding)
                .fillMaxSize(),
            contentAlignment = if (courses!!.isEmpty()) {
                Alignment.Center
            } else {
                Alignment.TopCenter
            }
        ) {
            if (courses!!.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.no_classes),
                    color = BlackLight,
                    fontSize = 14.sp
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    courses?.forEach {
                        item {
                            CourseItem(
                                email = user!!.email,
                                course = it,
                                onClick = {
                                    navHostController.navigate(MainNavigation.CourseScreen.route)
                                    sharedViewModel.setCurrentCourse(it.token)
                                }
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(72.dp))
                    }
                }
            }
        }
    }

    BackHandler {
    }
}