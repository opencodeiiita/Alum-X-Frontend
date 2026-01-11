package com.geekhaven.alumx.components.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.R
import com.geekhaven.alumx.presentation.chats.ChatBadgeStyle
import com.geekhaven.alumx.presentation.chats.ChatThread
import com.geekhaven.alumx.ui.theme.PrimaryBlue

@Composable
fun ChatListItem(
    thread: ChatThread,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val cardColor = Color(0xFF131A26)
    val subTextColor = Color(0xFF92A3B8)

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = cardColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChatAvatar(thread)

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = thread.name,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = true)
                    )

                    thread.tagLabel?.let { label ->
                        ChatTag(label = label, style = thread.tagStyle ?: ChatBadgeStyle.Student)
                    }

                    if (thread.isPinned) {
                        Icon(
                            imageVector = Icons.Filled.PushPin,
                            contentDescription = "Pinned chat",
                            tint = PrimaryBlue,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = thread.lastMessage,
                    color = subTextColor,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = thread.timestampLabel,
                    color = subTextColor,
                    style = MaterialTheme.typography.labelMedium
                )

                if (thread.unreadCount > 0) {
                    Surface(
                        shape = CircleShape,
                        color = PrimaryBlue
                    ) {
                        Text(
                            text = thread.unreadCount.toString(),
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }
        }
    }
}

@Composable
private fun ChatAvatar(thread: ChatThread) {
    Box(
        modifier = Modifier.size(56.dp)
    ) {
        Image(
            painter = painterResource(id = thread.avatarRes),
            contentDescription = "${thread.name} avatar",
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        if (thread.isGroup) {
            Surface(
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.BottomEnd),
                shape = CircleShape,
                color = Color(0xFF1F2738),
                border = BorderStroke(1.dp, Color(0x33175BE5))
            ) {
                Icon(
                    imageVector = Icons.Filled.People,
                    contentDescription = "Group chat",
                    tint = PrimaryBlue,
                    modifier = Modifier
                        .padding(3.dp)
                        .size(12.dp)
                )
            }
        }
    }
}

@Composable
fun ChatTag(label: String, style: ChatBadgeStyle) {
    val (container, content) = when (style) {
        ChatBadgeStyle.Mentor -> Color(0xFF3B2A73) to Color(0xFFE5D8FF)
        ChatBadgeStyle.Student, ChatBadgeStyle.Alumni -> PrimaryBlue.copy(alpha = 0.18f) to PrimaryBlue
        ChatBadgeStyle.Year -> Color(0xFF2A303C) to Color.White
        ChatBadgeStyle.Group -> Color(0xFF0F2F4F) to Color(0xFF7FC0FF)
    }

    Surface(
        color = container,
        contentColor = content,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, container.copy(alpha = 0.6f))
    ) {
        Text(
            text = label,
            color = content,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF131924)
@Composable
private fun ChatListItemPreview() {
    ChatListItem(
        thread = ChatThread(
            id = 1,
            name = "Sarah Jenkins",
            lastMessage = "Hey! I saw your resume update. Let's connect soon.",
            timestampLabel = "1h ago",
            avatarRes = R.drawable.imagecopy,
            unreadCount = 1,
            tagLabel = "Mentor",
            tagStyle = ChatBadgeStyle.Mentor
        )
    )
}
