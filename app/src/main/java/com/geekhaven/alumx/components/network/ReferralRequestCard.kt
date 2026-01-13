package com.geekhaven.alumx.components.network

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geekhaven.alumx.R
import com.geekhaven.alumx.ui.theme.PrimaryBlue

@Composable
fun ReferralRequestCard(
    name: String,
    subtitle: String,
    targetLabel: String,
    message: String,
    onAccept: () -> Unit,
    onDecline: () -> Unit,
    modifier: Modifier = Modifier,
    profileRes: Int = R.drawable.placeholder2,
    isOnline: Boolean = false
) {
    val cardBg = Color(0xFF0F1828)
    val subTextColor = Color(0xFF8FA0B6)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(56.dp)) {
                    Image(
                        painter = painterResource(profileRes),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    if (isOnline) {
                        Box(
                            modifier = Modifier
                                .size(11.dp)
                                .align(Alignment.BottomEnd)
                                .clip(CircleShape)
                                .background(Color(0xFF2ED573))
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = subtitle,
                        color = subTextColor,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    AssistChip(
                        onClick = {},
                        label = { Text(targetLabel.uppercase(), color = Color.White) },
                        leadingIcon = {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Work,
                                contentDescription = null,
                                tint = Color.White
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = PrimaryBlue.copy(alpha = 0.14f),
                            labelColor = Color.White
                        ),
                        border = BorderStroke(1.dp, PrimaryBlue.copy(alpha = 0.35f))
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "\"$message\"",
                color = Color.White.copy(alpha = 0.85f),
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onAccept,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text("Accept", fontWeight = FontWeight.SemiBold)
                }
                OutlinedButton(
                    onClick = onDecline,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.25f))
                ) {
                    Text("Decline")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReferralRequestCardPreview() {
    ReferralRequestCard(
        name = "Alex Rivera",
        subtitle = "CS Senior - State University",
        targetLabel = "Target: Google - SWE",
        message = "Hi! I've been following your work in cloud infrastructure and distributed systems.",
        onAccept = {},
        onDecline = {},
        isOnline = true
    )
}
