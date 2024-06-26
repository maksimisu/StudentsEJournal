package com.baisamy.studentsejournal.presentation.screen.user_notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.models.Notification
import com.baisamy.studentsejournal.presentation.ui.components.Notification
import com.baisamy.studentsejournal.presentation.ui.theme.Headline
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNotificationsScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val notifications by remember {
        mutableStateOf(
            listOf<Notification>(

            )
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
                        stringResource(id = R.string.notifications).uppercase(),
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
        LazyColumn(
            modifier = Modifier
                .padding(top = topPadding + 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            notifications.forEach {
                item {
                    Notification(
                        notification = it,
                        positiveAction = { /*TODO*/ },
                        negativeAction = { /*TODO*/ })
                }
            }
        }
    }
}