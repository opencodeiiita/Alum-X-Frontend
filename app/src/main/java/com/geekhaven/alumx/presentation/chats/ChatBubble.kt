package com.geekhaven.alumx.presentation.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.ui.theme.PrimaryBlue

@Composable
fun ChatBubble(message: ChatMessage) {
    val alignment = if (message.isMine) Alignment.End else Alignment.Start
    val bgColor = if (message.isMine) PrimaryBlue else Color(0xFF1C2435)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = bgColor,
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .widthIn(max = 260.dp)
        ) {
            Text(
                text = message.text,
                color = Color.White,
                fontSize = 14.sp
            )
        }

        Text(
            text = message.time,
            color = Color.White.copy(alpha = 0.4f),
            fontSize = 11.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}
