package com.geekhaven.alumx.components.network

import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geekhaven.alumx.R
import com.geekhaven.alumx.ui.theme.PrimaryBlue
import com.geekhaven.alumx.ui.theme.SurfaceColor
import androidx.compose.ui.layout.ContentScale

@Composable
fun RecommendedProfileCard(
    name: String,
    designation: String,
    company: String,
    tag: String,
    onConnect: () -> Unit,
    modifier: Modifier = Modifier,
    profileRes: Int = R.drawable.placeholder2,
    isActive: Boolean = true
) {
    val cardBg = Color(0xFF141C2F)
    val subTextColor = Color(0xFF8FA0B6)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(56.dp)) {
                Image(
                    painter = painterResource(profileRes),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                if (isActive) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .align(Alignment.BottomEnd)
                            .clip(CircleShape)
                            .background(Color(0xFF25C06D))
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = name,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    AssistChip(
                        onClick = {},
                        label = { Text(tag.uppercase(), color = PrimaryBlue) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = PrimaryBlue.copy(alpha = 0.12f),
                            labelColor = PrimaryBlue
                        ),
                        border = BorderStroke(1.dp, PrimaryBlue.copy(alpha = 0.4f))
                    )
                }
                Text(
                    text = designation,
                    color = subTextColor,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = company,
                    color = PrimaryBlue,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = onConnect) {
                Icon(
                    imageVector = Icons.Default.PersonAdd,
                    contentDescription = "Connect",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecommendedProfileCardPreview() {
    RecommendedProfileCard(
        name = "Sarah Jenkins",
        designation = "Senior Product Manager",
        company = "Google",
        tag = "MENTOR",
        onConnect = {}
    )
}
