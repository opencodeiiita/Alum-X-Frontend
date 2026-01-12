package com.geekhaven.alumx.presentation.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.geekhaven.alumx.ui.theme.PrimaryBlue
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.InsertEmoticon


@Composable
fun ChatInputBar(
    onSend: (String) -> Unit,
    onEmojiClick: () -> Unit = {},
    onAttachClick: () -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0F1624))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // ➕ Attachment button
        IconButton(onClick = onAttachClick) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Attach",
                tint = Color.White.copy(alpha = 0.8f)
            )
        }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Type a message…") },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = onEmojiClick) {
                    Icon(
                        imageVector = Icons.Default.InsertEmoticon,
                        contentDescription = "Emoji",
                        tint = Color.White.copy(alpha = 0.7f)
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1C2435),
                unfocusedContainerColor = Color(0xFF1C2435),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = PrimaryBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.width(6.dp))

        IconButton(
            onClick = {
                if (text.isNotBlank()) {
                    onSend(text.trim())
                    text = ""
                }
            },
            modifier = Modifier
                .size(44.dp)
                .background(PrimaryBlue, CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send",
                tint = Color.White
            )
        }
    }

}
