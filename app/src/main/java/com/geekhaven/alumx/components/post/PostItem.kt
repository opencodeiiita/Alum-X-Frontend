package com.geekhaven.alumx.components.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
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
import com.geekhaven.alumx.model.Post

@Composable
fun PostItem(
    post: Post
) {
     val cardBg = Color(0xFF141C2F)
     val textColor = Color.White
     val subTextColor = Color.Gray

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = post.profileRes),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
              )

            Spacer(modifier = Modifier.width(10.dp))
                 Column {
                    Text(
                         text = post.authorName,
                        style = MaterialTheme.typography.titleMedium,
                         color = textColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = post.authorDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = subTextColor
                    )
             }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "2h ago",
                    style = MaterialTheme.typography.bodySmall,
                    color = subTextColor
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = post.postText,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

    
            Image(
                painter = painterResource(id = post.imageRes),
                contentDescription = post.placeName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Like", tint = subTextColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.likes}", color = subTextColor)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Email, contentDescription = "Comment", tint = subTextColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.comments}", color = subTextColor)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Refresh, contentDescription = "Repost", tint = subTextColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${post.reposts}", color = subTextColor)
                }
            }

        Divider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = Color(0xFF2B3240)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Like", tint = subTextColor)
             Icon(Icons.Default.Email, contentDescription = "Comment", tint = subTextColor)
            Icon(Icons.Default.Refresh, contentDescription = "Repost", tint = subTextColor)
             Icon(Icons.Default.Share, contentDescription = "Share", tint = subTextColor)
        }
     }
    }
}
