package com.geekhaven.alumx.presentation.chats

import androidx.lifecycle.ViewModel
import com.geekhaven.alumx.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChatsViewModel : ViewModel() {

    private val allChats = listOf(
        ChatThread(
            id = 1,
            name = "Mentorship Group",
            lastMessage = "David: When is the next meeting?",
            timestampLabel = "12m ago",
            avatarRes = R.drawable.logo,
            unreadCount = 3,
            tagLabel = "Group",
            tagStyle = ChatBadgeStyle.Group,
            isPinned = true,
            isGroup = true
        ),
        ChatThread(
            id = 2,
            name = "Sarah Jenkins",
            lastMessage = "Hey! I saw your resume update. Look...",
            timestampLabel = "1h ago",
            avatarRes = R.drawable.imagecopy,
            unreadCount = 1,
            tagLabel = "'18",
            tagStyle = ChatBadgeStyle.Year
        ),
        ChatThread(
            id = 3,
            name = "Prof. Alan Grant",
            lastMessage = "Thanks for reaching out, let's connect soon.",
            timestampLabel = "Yesterday",
            avatarRes = R.drawable.placeholder1,
            tagLabel = "Mentor",
            tagStyle = ChatBadgeStyle.Mentor
        ),
        ChatThread(
            id = 4,
            name = "Tech Innovators Club",
            lastMessage = "Meeting tomorrow at 5 PM in the main hall.",
            timestampLabel = "Tue",
            avatarRes = R.drawable.placeholder3,
            tagLabel = "Club",
            tagStyle = ChatBadgeStyle.Group,
            isGroup = true
        ),
        ChatThread(
            id = 5,
            name = "Emily White",
            lastMessage = "Can you send the slides?",
            timestampLabel = "Mon",
            avatarRes = R.drawable.image,
            tagLabel = "'20",
            tagStyle = ChatBadgeStyle.Year
        )
    )

    private val _uiState = MutableStateFlow(
        ChatsUiState(
            chats = allChats
        )
    )
    val uiState: StateFlow<ChatsUiState> = _uiState.asStateFlow()

    fun onSearchChange(query: String) {
        _uiState.update { state ->
            state.copy(
                searchQuery = query,
                chats = filterChats(query)
            )
        }
    }

    private fun filterChats(query: String): List<ChatThread> {
        val normalized = query.trim()
        if (normalized.isBlank()) return allChats

        return allChats.filter { chat ->
            listOf(chat.name, chat.lastMessage, chat.tagLabel.orEmpty())
                .any { it.contains(normalized, ignoreCase = true) }
        }
    }
}
