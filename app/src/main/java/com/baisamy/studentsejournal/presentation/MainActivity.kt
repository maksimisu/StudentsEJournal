package com.baisamy.studentsejournal.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.baisamy.studentsejournal.presentation.navigation.SetUpNavHost
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            val sharedViewModel = SharedViewModel()
            SetUpNavHost(navHostController = navHostController, sharedViewModel)
        }
    }
}