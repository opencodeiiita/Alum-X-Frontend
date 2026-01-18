package com.geekhaven.alumx.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.ViewModule
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.R

data class ProfileOption(
    val title: String,
    val subtitle: String? = null,
    val icon: ImageVector,
    val iconTint: Color,
    val iconBackground: Color,
    val trailingIcon: ImageVector? = Icons.AutoMirrored.Filled.KeyboardArrowRight,
    val trailingTint: Color = Color(0xFF6E7B95)
)

private val personalInfoOptions = listOf(
    ProfileOption(
        title = "About",
        subtitle = "Passionate developer & mentor",
        icon = Icons.Outlined.Info,
        iconTint = Color(0xFF54B6FF),
        iconBackground = Color(0xFF12365B)
    ),
    ProfileOption(
        title = "Username",
        subtitle = "@alex_johnson",
        icon = Icons.Outlined.AlternateEmail,
        iconTint = Color(0xFFC48BFF),
        iconBackground = Color(0xFF2C1F4B)
    )
)

private val educationExperienceOptions = listOf(
    ProfileOption(
        title = "Internships",
        icon = Icons.Outlined.School,
        iconTint = Color(0xFF7C8CFF),
        iconBackground = Color(0xFF242A53)
    ),
    ProfileOption(
        title = "Experience",
        icon = Icons.Outlined.WorkOutline,
        iconTint = Color(0xFFFF9C57),
        iconBackground = Color(0xFF3B2719)
    )
)

private val skillsOptions = listOf(
    ProfileOption(
        title = "Programming",
        subtitle = "Python, JS, Swift",
        icon = Icons.Outlined.Code,
        iconTint = Color(0xFF2DD1B4),
        iconBackground = Color(0xFF1D3A36)
    ),
    ProfileOption(
        title = "Frameworks",
        icon = Icons.Outlined.ViewModule,
        iconTint = Color(0xFF2FA7E8),
        iconBackground = Color(0xFF1E2F3A)
    ),
    ProfileOption(
        title = "Soft Skills",
        icon = Icons.Outlined.EmojiEmotions,
        iconTint = Color(0xFFFF6B8E),
        iconBackground = Color(0xFF3A1E2D)
    )
)

private val portfolioOptions = listOf(
    ProfileOption(
        title = "LinkedIn",
        icon = Icons.Outlined.Link,
        iconTint = Color(0xFF5FA5FF),
        iconBackground = Color(0xFF1C2E4C),
        trailingIcon = Icons.Outlined.OpenInNew,
        trailingTint = Color(0xFF7DA3FF)
    ),
    ProfileOption(
        title = "GitHub",
        icon = Icons.Outlined.Code,
        iconTint = Color(0xFF9DA7C3),
        iconBackground = Color(0xFF242933),
        trailingIcon = Icons.Outlined.OpenInNew,
        trailingTint = Color(0xFF7DA3FF)
    ),
    ProfileOption(
        title = "Portfolio URL",
        icon = Icons.Outlined.Public,
        iconTint = Color(0xFFF3638A),
        iconBackground = Color(0xFF3A1F32),
        trailingIcon = Icons.Outlined.OpenInNew,
        trailingTint = Color(0xFF7DA3FF)
    )
)

private val accomplishmentsOptions = listOf(
    ProfileOption(
        title = "Certifications",
        icon = Icons.Outlined.CheckCircle,
        iconTint = Color(0xFFF4B400),
        iconBackground = Color(0xFF3B2E14)
    ),
    ProfileOption(
        title = "Projects",
        subtitle = "3 Active Projects",
        icon = Icons.Outlined.Workspaces,
        iconTint = Color(0xFF39D27C),
        iconBackground = Color(0xFF1F3326)
    ),
    ProfileOption(
        title = "Hobbies",
        icon = Icons.Outlined.SportsEsports,
        iconTint = Color(0xFF9D6EFF),
        iconBackground = Color(0xFF2E1E42)
    )
)

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    onLogoutClick: () -> Unit = {}
) {
    val backgroundBrush = Brush.verticalGradient(
        listOf(Color(0xFF0B1220), Color(0xFF0C1422))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            contentPadding = PaddingValues(top = 20.dp, bottom = 32.dp)
        ) {
            item {
                ProfileHeader(onLogoutClick = onLogoutClick)
            }
            item {
                ProfileSection(
                    title = "Personal Info",
                    options = personalInfoOptions
                )
            }
            item {
                ProfileSection(
                    title = "Education & Experience",
                    options = educationExperienceOptions
                )
            }
            item {
                ProfileSection(
                    title = "Skills",
                    options = skillsOptions
                )
            }
            item {
                ProfileSection(
                    title = "Portfolio & Links",
                    options = portfolioOptions
                )
            }
            item {
                ProfileSection(
                    title = "Accomplishments",
                    options = accomplishmentsOptions
                )
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    onLogoutClick: () -> Unit
) {
    val cardBrush = Brush.verticalGradient(
        listOf(Color(0xFF1A2741), Color(0xFF121B2C))
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(cardBrush)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.placeholder2),
                        contentDescription = "Profile photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .matchParentSize()
                            .clip(CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .align(Alignment.BottomEnd)
                            .clip(CircleShape)
                            .background(Color(0xFF3DDC84))
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Alex Johnson",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                    Text(
                        text = "Class of 2022 - Computer Science",
                        color = Color(0xFF9AA9C3),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "B.Tech, Boston University",
                        color = Color(0xFF6E7A95),
                        fontSize = 13.sp
                    )
                }

                IconButton(
                    onClick = onLogoutClick,
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color(0xFF1E2A3E), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = "Logout",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileSection(
    title: String,
    options: List<ProfileOption>,
    modifier: Modifier = Modifier
) {
    val labelColor = Color(0xFF7D8BA5)
    val cardColor = Color(0xFF182235)

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title.uppercase(),
            color = labelColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                options.forEachIndexed { index, option ->
                    ProfileOptionRow(option)
                    if (index != options.lastIndex) {
                        Divider(
                            color = Color.White.copy(alpha = 0.06f),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileOptionRow(option: ProfileOption) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(option.iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = option.icon,
                contentDescription = option.title,
                tint = option.iconTint
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = option.title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            option.subtitle?.let {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = it,
                    color = Color(0xFF8FA1BE),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        option.trailingIcon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = option.trailingTint
            )
        }
    }
}
