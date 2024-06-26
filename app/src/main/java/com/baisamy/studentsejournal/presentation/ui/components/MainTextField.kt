package com.baisamy.studentsejournal.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.baisamy.studentsejournal.presentation.ui.theme.BlackLight
import com.baisamy.studentsejournal.presentation.ui.theme.Input
import com.baisamy.studentsejournal.presentation.ui.theme.White

@Composable
fun MainTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    isMaxWidth: Boolean = true,
    enabled: Boolean = true
) {
    BasicTextField(
        modifier = modifier
            .height(32.dp)
            .then(
                if (isMaxWidth) Modifier.fillMaxWidth()
                else Modifier
            )
            .clip(RoundedCornerShape(5.dp))
            .background(White)
            .border(
                width = 1.dp,
                color = BlackLight,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(vertical = 5.dp, horizontal = 8.dp),
        enabled = enabled,
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        textStyle = Input,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        style = Input
                    )
                }
                innerTextField()
            }
        }
    )
}