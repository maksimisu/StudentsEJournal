package com.baisamy.studentsejournal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.baisamy.studentsejournal.presentation.screen.course.CourseScreen
import com.baisamy.studentsejournal.presentation.screen.course_notifications.CourseNotificationsScreen
import com.baisamy.studentsejournal.presentation.screen.home.HomeScreen
import com.baisamy.studentsejournal.presentation.screen.lesson.LessonScreen
import com.baisamy.studentsejournal.presentation.screen.login.LoginScreen
import com.baisamy.studentsejournal.presentation.screen.profile.ProfileScreen
import com.baisamy.studentsejournal.presentation.screen.register.RegisterScreen
import com.baisamy.studentsejournal.presentation.screen.student.StudentScreen
import com.baisamy.studentsejournal.presentation.screen.task.TaskScreen
import com.baisamy.studentsejournal.presentation.screen.user_notifications.UserNotificationsScreen
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

sealed class MainNavigation(val route: String) {
    data object LoginScreen : MainNavigation("login")
    data object RegisterScreen : MainNavigation("register")
    data object HomeScreen : MainNavigation("home")
    data object ProfileScreen : MainNavigation("profile")
    data object UserNotificationsScreen : MainNavigation("user_notifications")
    data object CourseScreen : MainNavigation("course")
    data object CourseNotificationsScreen : MainNavigation("course_notifications")
    data object LessonScreen : MainNavigation("lesson")
    data object TaskScreen : MainNavigation("task")
    data object StudentScreen : MainNavigation("student")
}

@Composable
fun SetUpNavHost(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = MainNavigation.LoginScreen.route
    ) {

        // Login
        composable(
            route = MainNavigation.LoginScreen.route
        ) {
            LoginScreen(navHostController, sharedViewModel)
        }

        // Register
        composable(
            route = MainNavigation.RegisterScreen.route
        ) {
            RegisterScreen(navHostController, sharedViewModel)
        }

        // Home
        composable(
            route = MainNavigation.HomeScreen.route
        ) {
            HomeScreen(navHostController, sharedViewModel)
        }

        // Profile
        composable(
            route = MainNavigation.ProfileScreen.route
        ) {
            ProfileScreen(navHostController, sharedViewModel)
        }

        // User Notifications
        composable(
            route = MainNavigation.UserNotificationsScreen.route
        ) {
            UserNotificationsScreen(navHostController, sharedViewModel)
        }

        // Course
        composable(
            route = MainNavigation.CourseScreen.route
        ) {
            CourseScreen(navHostController, sharedViewModel)
        }

        // Course Notifications
        composable(
            route = MainNavigation.CourseNotificationsScreen.route
        ) {
            CourseNotificationsScreen(navHostController, sharedViewModel)
        }

        // Student
        composable(
            route = MainNavigation.StudentScreen.route
        ) {
            StudentScreen(navHostController, sharedViewModel)
        }

        // Lesson
        composable(
            route = MainNavigation.LessonScreen.route
        ) {
            LessonScreen(navHostController, sharedViewModel)
        }

        // Task
        composable(
            route = MainNavigation.TaskScreen.route
        ) {
            TaskScreen(navHostController, sharedViewModel)
        }
    }
}