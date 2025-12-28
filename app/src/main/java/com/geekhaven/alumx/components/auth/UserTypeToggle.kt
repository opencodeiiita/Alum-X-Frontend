package com.geekhaven.alumx.components.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.presentation.auth.register.UserType
import com.geekhaven.alumx.ui.theme.PrimaryBlue
import com.geekhaven.alumx.ui.theme.SurfaceColor

@Composable
fun UserTypeToggle(
    selected: UserType,
    onSelect: (UserType) -> Unit
) {
    Column() {

        Text(
            modifier = Modifier.padding(start = 5.dp),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Color.White,
            text = "I am a"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceColor)
                .padding(4.dp)
        ) {
            ToggleOption(
                text = "Student",
                selected = selected == UserType.STUDENT,
                modifier = Modifier.weight(1f),
                onClick = { onSelect(UserType.STUDENT) }
            )

            ToggleOption(
                text = "Alumni",
                selected = selected == UserType.ALUMNI,
                modifier = Modifier.weight(1f),
                onClick = { onSelect(UserType.ALUMNI) }
            )
        }
    }
}
@Composable
private fun ToggleOption(
    text: String,
    selected: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                if (selected) PrimaryBlue else Color.Transparent
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (selected) Color.White else Color.White.copy(0.6f)
        )
    }
}
