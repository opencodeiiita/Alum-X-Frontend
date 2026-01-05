package com.geekhaven.alumx.components.network

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geekhaven.alumx.R
import androidx.compose.ui.layout.ContentScale
import com.geekhaven.alumx.ui.theme.PrimaryBlue

@Composable
fun IncomingRequestCard(
    name: String,
    designation: String,
    timeAgo: String,
    onIgnore: () -> Unit,
    onReview: () -> Unit,
    modifier: Modifier = Modifier,
    profileRes: Int = R.drawable.placeholder1,
    isNew: Boolean = true
) {
    val cardBg = Color(0xFF141C2F)
    val subTextColor = Color(0xFF8FA0B6)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(profileRes),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = designation,
                        color = subTextColor,
                        style = MaterialTheme.typography.bodySmall
                    )
                    if (isNew) {
                        Spacer(modifier = Modifier.height(6.dp))
                        AssistChip(
                            onClick = {},
                            label = { Text("NEW", color = Color.White) },
                            colors = AssistChipDefaults.assistChipColors(containerColor = PrimaryBlue)
                        )
                    }
                }

                Text(
                    text = timeAgo,
                    color = subTextColor,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onIgnore,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = subTextColor),
                    border = BorderStroke(1.dp, Color(0xFF2B3240))
                ) {
                    Text("Ignore")
                }

                Button(
                    onClick = onReview,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text("Review Request")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IncomingRequestCardPreview() {
    IncomingRequestCard(
        name = "Sarah Jenkins",
        designation = "Seeking: Product Design @ Uber",
        timeAgo = "2h ago",
        onIgnore = {},
        onReview = {}
    )
}
