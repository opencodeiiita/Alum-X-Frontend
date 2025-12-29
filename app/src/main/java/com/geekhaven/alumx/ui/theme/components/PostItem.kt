package com.example.postcard.ui.theme.components.post

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostItem(
    authorName: String,
    authorSubtitle: String,
    time: String,
    postText: String,
    likes: Int,
    comments: Int,
    reposts: Int,
    showImage: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF141B33)
        )
    )
    {
            Column(modifier = Modifier.padding(12.dp)) {


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(Color.Gray, CircleShape)
                    )

                    Spacer(Modifier.width(10.dp))

                    Column(Modifier.weight(1f)) {
                        Text(authorName, fontWeight = FontWeight.Bold, color = Color.White)
                        Text(authorSubtitle, fontSize = 12.sp, color = Color.Gray)
                        Text(time, fontSize = 12.sp, color = Color.Gray)
                    }

                    Icon(Icons.Default.MoreVert, contentDescription = null)
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = postText,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )

                Spacer(Modifier.height(8.dp))

                if (showImage) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(Color.Black)
                    )
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "👍 $likes   $comments comments   $reposts reposts",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

}
