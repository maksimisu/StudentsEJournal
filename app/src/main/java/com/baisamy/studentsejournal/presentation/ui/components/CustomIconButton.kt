package com.baisamy.studentsejournal.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.presentation.ui.theme.BlackLight
import com.baisamy.studentsejournal.presentation.ui.theme.Title

@Composable
fun CustomIconButton(
    icon: ImageVector,
    title: String,
    action: () -> Unit,
    tint: Color = BlackLight
) {
    Column(
        modifier = Modifier
            .clickable { action() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = tint,
            modifier = Modifier
                .size(72.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title.uppercase(),
            style = Title,
            color = tint
        )
    }
}

@Composable
@Preview(showBackground = false)
fun IconButtonPreview() {
    CustomIconButton(
        icon = Icons.Outlined.PeopleOutline,
        title = stringResource(id = R.string.students),
        action = { /*TODO*/ }
    )
}