package com.baisamy.studentsejournal.presentation.models

data class Task(
    val id: Int,
    val title: String,
    val desc: String,
    val date: String,
    val completed: List<Profile>
)