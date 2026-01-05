package com.geekhaven.alumx.presentation.network

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.geekhaven.alumx.R
import com.geekhaven.alumx.components.network.IncomingRequestCard
import com.geekhaven.alumx.components.network.RecommendedProfileCard


data class IncomingRequest(
    val name: String,
    val designation: String,
    val timeAgo: String,
    val profileRes: Int,
    val isNew: Boolean
)

data class RecommendedProfile(
    val name: String,
    val designation: String,
    val company: String,
    val tag: String,
    val profileRes: Int,
    val isActive: Boolean
)

@Composable
fun NetworkScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues()
) {
    val incomingRequests = remember {
        listOf(
            IncomingRequest(
                name = "Sarah Jenkins",
                designation = "Seeking: Product Design @ Uber",
                timeAgo = "2h ago",
                profileRes = R.drawable.imagecopy,
                isNew = true
            ),
            IncomingRequest(
                name = "Aarav Mehta",
                designation = "Looking for Data Science roles",
                timeAgo = "1d ago",
                profileRes = R.drawable.image,
                isNew = false
            )
        )
    }
    val recommendedProfiles = remember {
        listOf(
            RecommendedProfile(
                name = "Sarah Jenkins",
                designation = "Senior Product Manager",
                company = "Google",
                tag = "MENTOR",
                profileRes = R.drawable.imagecopy,
                isActive = true
            ),
            RecommendedProfile(
                name = "Harsh Patel",
                designation = "Backend Engineer",
                company = "Stripe",
                tag = "STUDENT",
                profileRes = R.drawable.image,
                isActive = true
            )
        )
    }

    LazyColumn(
        modifier = modifier.padding(innerPadding),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                text = "Incoming Requests",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(incomingRequests) { request ->
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

        items(recommendedProfiles) { profile ->
            RecommendedProfileCard(
                name = profile.name,
                designation = profile.designation,
                company = profile.company,
                tag = profile.tag,
                onConnect = {},
                profileRes = profile.profileRes,
                isActive = profile.isActive
            )
        }
    }
}
