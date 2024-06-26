package com.baisamy.studentsejournal.presentation.models

import androidx.compose.ui.graphics.Color
import com.baisamy.studentsejournal.presentation.ui.theme.Green
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.Red
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteDark

enum class ActionType(val color: Color) {
    PRIMARY(LightBlue),
    SECONDARY(WhiteDark),
    POSITIVE(Green),
    NEGATIVE(Red)
}
