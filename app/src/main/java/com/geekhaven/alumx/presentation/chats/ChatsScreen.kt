package com.geekhaven.alumx.presentation.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geekhaven.alumx.components.chat.ChatListItem
import com.geekhaven.alumx.ui.theme.DeepBlueBG
import com.geekhaven.alumx.ui.theme.PrimaryBlue

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: ChatsViewModel = viewModel(),
    onComposeChatClick: () -> Unit = {},
    onChatClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlueBG)
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        ChatsHeader(onComposeChatClick)

        Spacer(modifier = Modifier.height(14.dp))

        ChatSearchBar(
            query = uiState.searchQuery,
            onQueryChange = viewModel::onSearchChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.White.copy(alpha = 0.06f)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(uiState.chats, key = { it.id }) { chat ->
                ChatListItem(
                    thread = chat,
                    onClick = { onChatClick(chat.id) }
                )
            }
        }
    }
}

@Composable
private fun ChatsHeader(onComposeChatClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Chats",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        Surface(
            modifier = Modifier.clickable(onClick = onComposeChatClick),
            shape = CircleShape,
            color = PrimaryBlue,
            shadowElevation = 8.dp
        ) {
            Icon(
                imageVector = Icons.Filled.Create,
                contentDescription = "Compose chat",
                tint = Color.White,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
private fun ChatSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val fieldColor = Color(0xFF1C2435)

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search alumni, mentors...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search chats",
                tint = Color.White.copy(alpha = 0.7f)
            )
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = fieldColor,
            unfocusedContainerColor = fieldColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = PrimaryBlue,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
            unfocusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
            disabledContainerColor = fieldColor
        )
    )
}
