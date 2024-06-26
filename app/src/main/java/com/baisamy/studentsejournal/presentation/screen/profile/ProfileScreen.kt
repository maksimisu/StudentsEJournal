package com.baisamy.studentsejournal.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.outlined.ExitToApp
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
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.navigation.MainNavigation
import com.baisamy.studentsejournal.presentation.ui.components.ActionButton
import com.baisamy.studentsejournal.presentation.ui.components.MainTextField
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Headline
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.Title
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val user by sharedViewModel.currentUser.observeAsState()
    val courses by sharedViewModel.courses.observeAsState()
    var coursesInProgress = 0
    var coursesCompleted = 0

    courses?.forEach {
        if (it.isRunning)
            coursesInProgress++
        else
            coursesCompleted++
    }

    var name by remember {
        mutableStateOf(user!!.name)
    }
    var email by remember {
        mutableStateOf(user!!.email)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.profile).uppercase(),
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
                                sharedViewModel.updateProfile(name)
                            }
                            .padding(8.dp),
                        imageVector = Icons.Outlined.Save,
                        contentDescription = stringResource(id = R.string.notifications)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clickable {
                                navHostController.navigate(MainNavigation.LoginScreen.route)
                                sharedViewModel.logOut()
                            }
                            .padding(8.dp),
                        imageVector = Icons.Outlined.ExitToApp,
                        contentDescription = stringResource(id = R.string.log_out)
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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = WhiteGray),
                shape = RoundedCornerShape(10.dp),
                shadowElevation = 4.dp,
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(id = R.string.overall_info).uppercase(),
                        style = Title
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.name),
                        style = Body
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MainTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        hint = stringResource(id = R.string.name)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.email),
                        style = Body
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MainTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        hint = stringResource(id = R.string.email),
                        enabled = false
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${stringResource(id = R.string.classes_in_progress)} $coursesInProgress",
                        style = Body
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${stringResource(id = R.string.classes_completed)} $coursesCompleted",
                        style = Body
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${stringResource(id = R.string.total_classes)} ${coursesInProgress + coursesCompleted}",
                        style = Body
                    )
                }
            }
            ActionButton(
                modifier = Modifier.padding(bottom = 24.dp),
                title = stringResource(id = R.string.delete_profile),
                actionType = ActionType.NEGATIVE
            ) {
                navHostController.navigate(MainNavigation.LoginScreen.route)
                sharedViewModel.deleteProfile()
            }
        }
    }
}