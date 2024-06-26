package com.baisamy.studentsejournal.presentation.models

import androidx.compose.ui.graphics.Color
import com.baisamy.studentsejournal.presentation.ui.theme.Blue
import com.baisamy.studentsejournal.presentation.ui.theme.Golden
import com.baisamy.studentsejournal.presentation.ui.theme.Green

enum class LessonType(
    val title: String,
    val color: Color
) {
    LECTURE("Lecture", Golden),
    PRACTICE("Practice", Green),
    LABORATORY("Laboratory", Blue)
}

fun String.getLessonTypeByTitle(): LessonType {
    return if (this == "Lecture") {
        LessonType.LECTURE
    } else if (this == "Practice") {
        LessonType.PRACTICE
    } else {
        LessonType.LABORATORY
    }
}