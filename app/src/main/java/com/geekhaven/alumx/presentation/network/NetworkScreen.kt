package com.geekhaven.alumx.presentation.network

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.R
import com.geekhaven.alumx.components.network.IncomingRequestCard
import com.geekhaven.alumx.components.network.RecommendedProfileCard
import com.geekhaven.alumx.ui.theme.PrimaryBlue

enum class NetworkRole { STUDENT, ALUMNI, MENTOR }

data class IncomingRequest(
    val name: String,
    val designation: String,
    val timeAgo: String,
    val profileRes: Int,
    val isNew: Boolean,
    val role: NetworkRole
)

data class RecommendedProfile(
    val name: String,
    val designation: String,
    val company: String,
    val profileRes: Int,
    val isActive: Boolean,
    val role: NetworkRole
)

@Composable
fun NetworkRootScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    searchQuery: String = ""
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf("Connections", "Referrals")

    Column(
        modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        NetworkTabs(
            tabs = tabs,
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        when (selectedTab) {
            0 -> NetworkScreen(
                searchQuery = searchQuery
            )
            1 -> ReferralScreen()
        }
    }
}


@Composable
fun NetworkTabs(
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Column {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            contentColor = PrimaryBlue,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab]),
                    color = PrimaryBlue,
                    height = 2.dp
                )
            },
            divider = {} // remove default divider
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (selectedTab == index)
                                PrimaryBlue
                            else
                                Color.White.copy(alpha = 0.45f)
                        )
                    }
                )
            }
        }

        // Custom subtle divider (matches screenshot)
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Color.White.copy(alpha = 0.08f)
        )
    }
}


@Composable
fun ReferralScreen() {
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        text = "Referrals coming soon 🚀",
        style = MaterialTheme.typography.titleMedium,
        color = Color.White,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun NetworkScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    searchQuery: String = ""
) {
    var selectedRole by remember { mutableStateOf<NetworkRole?>(null) }

    val filterLabels = mapOf<NetworkRole?, String>(
        null to "All",
        NetworkRole.STUDENT to "Students",
        NetworkRole.ALUMNI to "Alumni",
        NetworkRole.MENTOR to "Mentors"
    )
    val filterOptions = listOf<NetworkRole?>(null, NetworkRole.STUDENT, NetworkRole.ALUMNI, NetworkRole.MENTOR)

    val incomingRequests = remember {
        listOf(
            IncomingRequest(
                name = "Sarah Jenkins",
                designation = "Seeking: Product Design @ Uber",
                timeAgo = "2h ago",
                profileRes = R.drawable.imagecopy,
                isNew = true,
                role = NetworkRole.MENTOR
            ),
            IncomingRequest(
                name = "Aarav Mehta",
                designation = "Looking for Data Science roles",
                timeAgo = "1d ago",
                profileRes = R.drawable.image,
                isNew = false,
                role = NetworkRole.STUDENT
            ),
            IncomingRequest(
                name = "Priya Sharma",
                designation = "iOS Engineer @ Meta",
                timeAgo = "3d ago",
                profileRes = R.drawable.imagecopy,
                isNew = false,
                role = NetworkRole.ALUMNI
            )
        )
    }
    val recommendedProfiles = remember {
        listOf(
            RecommendedProfile(
                name = "Sarah Jenkins",
                designation = "Senior Product Manager",
                company = "Google",
                profileRes = R.drawable.imagecopy,
                isActive = true,
                role = NetworkRole.MENTOR
            ),
            RecommendedProfile(
                name = "Harsh Patel",
                designation = "Backend Engineer",
                company = "Stripe",
                profileRes = R.drawable.image,
                isActive = true,
                role = NetworkRole.STUDENT
            ),
            RecommendedProfile(
                name = "Priya Sharma",
                designation = "iOS Engineer",
                company = "Meta",
                profileRes = R.drawable.imagecopy,
                isActive = false,
                role = NetworkRole.ALUMNI
            )
        )
    }

    val filteredIncomingRequests = remember(searchQuery, selectedRole) {
        val normalizedQuery = searchQuery.trim()
        incomingRequests.filter { request ->
            val matchesRole = selectedRole?.let { request.role == it } ?: true
            val matchesQuery = normalizedQuery.isBlank() ||
                listOf(request.name, request.designation).any { it.contains(normalizedQuery, ignoreCase = true) }
            matchesRole && matchesQuery
        }
    }

    val filteredRecommendedProfiles = remember(searchQuery, selectedRole) {
        val normalizedQuery = searchQuery.trim()
        recommendedProfiles.filter { profile ->
            val matchesRole = selectedRole?.let { profile.role == it } ?: true
            val matchesQuery = normalizedQuery.isBlank() ||
                listOf(profile.name, profile.designation, profile.company).any { it.contains(normalizedQuery, ignoreCase = true) }
            matchesRole && matchesQuery
        }
    }

    LazyColumn(
        modifier = modifier.padding(innerPadding),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filterOptions) { role ->
                    val label = filterLabels[role] ?: "All"
                    val isSelected = selectedRole == role
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedRole = role },
                        label = {
                            Text(
                                text = label,
                                fontSize = 14.sp
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color(0xFF141C2F),
                            labelColor = Color.White.copy(alpha = 0.7f),
                            selectedContainerColor = PrimaryBlue,
                            selectedLabelColor = Color.White
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = Color.White.copy(alpha = 0.1f),
                            selectedBorderColor = PrimaryBlue,
                            borderWidth = 1.dp
                        )
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Pending Requests",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(filteredIncomingRequests) { request ->
            IncomingRequestCard(
                name = request.name,
                designation = request.designation,
                timeAgo = request.timeAgo,
                onIgnore = {},
                onReview = {},
                profileRes = request.profileRes,
                isNew = request.isNew
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Recommended Profiles",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(filteredRecommendedProfiles) { profile ->
            RecommendedProfileCard(
                name = profile.name,
                designation = profile.designation,
                company = profile.company,
                tag = profile.role.name,
                onConnect = {},
                profileRes = profile.profileRes,
                isActive = profile.isActive
            )
        }
    }
}
