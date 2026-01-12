package com.geekhaven.alumx.presentation.chats

data class ChatMessage(
    val id: Int,
    val text: String,
    val isMine: Boolean,
    val time: String
)
