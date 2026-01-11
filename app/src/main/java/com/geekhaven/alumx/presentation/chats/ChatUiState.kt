package com.geekhaven.alumx.presentation.chats

import androidx.annotation.DrawableRes

enum class ChatBadgeStyle {
    Mentor,
    Student,
    Alumni,
    Year,
    Group
}

data class ChatThread(
    val id: Int,
    val name: String,
    val lastMessage: String,
    val timestampLabel: String,
    @DrawableRes val avatarRes: Int,
    val unreadCount: Int = 0,
    val tagLabel: String? = null,
    val tagStyle: ChatBadgeStyle? = null,
    val isPinned: Boolean = false,
    val isGroup: Boolean = false
)

data class ChatsUiState(
    val searchQuery: String = "",
    val chats: List<ChatThread> = emptyList()
)
