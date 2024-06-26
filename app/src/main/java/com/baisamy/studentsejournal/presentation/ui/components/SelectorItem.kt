package com.baisamy.studentsejournal.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.models.LessonType
import com.baisamy.studentsejournal.presentation.ui.theme.BlackLight
import com.baisamy.studentsejournal.presentation.ui.theme.Input
import com.baisamy.studentsejournal.presentation.ui.theme.White

@Composable
fun LessonTypeSelector(
    modifier: Modifier = Modifier,
    currentType: LessonType,
    onTypeChange: (LessonType) -> Unit,
    isMaxWidth: Boolean = true,
) {
    val lessonTypes = listOf(LessonType.LECTURE, LessonType.PRACTICE, LessonType.LABORATORY)
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .height(32.dp)
            .then(
                if (isMaxWidth) Modifier.fillMaxWidth()
                else Modifier
            ),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = BlackLight
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                }
                .padding(vertical = 5.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentType.title,
                style = Input,
                color = currentType.color
            )
            Icon(
                imageVector = if (!expanded) {
                    Icons.Outlined.ArrowDropDown
                } else {
                    Icons.Outlined.ArrowDropUp
                },
                contentDescription = stringResource(id = R.string.select),
                tint = BlackLight
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            lessonTypes.forEach {
                if (it != currentType) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = it.title,
                                style = Input,
                                color = it.color
                            )
                        },
                        onClick = {
                            onTypeChange(it)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun LessonTypeSelectorPreview() {
    var selected by remember { mutableStateOf(LessonType.LECTURE) }
    LessonTypeSelector(
        currentType = selected,
        onTypeChange = { selected = it }
    )
}