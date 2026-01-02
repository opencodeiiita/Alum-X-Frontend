package com.geekhaven.alumx.presentation.home

import androidx.lifecycle.ViewModel
import com.geekhaven.alumx.R
import com.geekhaven.alumx.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(posts = getSamplePosts()))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onSearchChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onBottomNavClick(index: Int) {
        _uiState.update { it.copy(selectedBottomIndex = index) }
    }

    private fun getSamplePosts(): List<Post> {
        return listOf(
            Post(
                authorName = "Sarah Jenkins",
                authorDescription = "Senior Product Manager at TechCorp • Class of '20",
                postText = "Making a career change is never easy, especially five years post-graduation. When I first graduated with my Mechanical Engineering degree, I thought I had my entire trajectory mapped out...",
                likes = 124,
                comments = 45,
                reposts = 12,
                placeName = "Career Journey",
                imageRes = R.drawable.placeholder2,
                postCategory = com.geekhaven.alumx.model.PostCategory.ANNOUNCEMENTS,
                title = "Navigating the Career Pivot: From Engineering to Product Management",
                tags = listOf("CAREER", "TECH", "ALUMNI STORIES"),
                fullContent = "Making a career change is never easy, especially five years post-graduation. When I first graduated with my Mechanical Engineering degree, I thought I had my entire trajectory mapped out. I'd work in automotive, maybe move to aerospace, and eventually lead a technical team.\n\nThe Pivot Point\n\nThe transition wasn't overnight. It started with side projects and coffee chats with alumni. I learned that Product Management sits at the intersection of technology, business, and user experience—a perfect blend for my curiosity.\n\n\"The most valuable skill you can bring from engineering to product is the ability to break down complex problems into manageable chunks.\"\n\nFor anyone looking to make a similar jump, my advice is simple: start acting like a PM in your current role. Take ownership of the \"why\", communicate clearly with stakeholders, and never stop learning about your users."
            ),
            Post(
                authorName = "Harsh Patel",
                authorDescription = "Travel Blogger & Photographer • Class of '19",
                postText = "Exploring Hội An, Quảng Nam, Vietnam. Beautiful streets, lanterns, and riverside views! 🏮✨",
                likes = 342,
                comments = 67,
                reposts = 23,
                placeName = "Hội An, Vietnam",
                imageRes = R.drawable.placeholder2,
                postCategory = com.geekhaven.alumx.model.PostCategory.EVENTS,
                tags = listOf("TRAVEL", "PHOTOGRAPHY", "LIFESTYLE"),
                fullContent = "Exploring Hội An, Quảng Nam, Vietnam. Beautiful streets, lanterns, and riverside views! 🏮✨\n\nThis ancient town is a UNESCO World Heritage site and it's easy to see why. The architecture, the culture, and the food scene here are absolutely incredible. Every corner offers a new photo opportunity.\n\nIf you're planning to visit Southeast Asia, Hội An should definitely be on your list!"
            ),
            Post(
                authorName = "Priya Sharma",
                authorDescription = "Software Engineer at Google • Class of '21",
                postText = "Just completed my first year at Google! Here's what I learned about imposter syndrome, team collaboration, and growth...",
                likes = 289,
                comments = 52,
                reposts = 18,
                placeName = "Mountain View, CA",
                imageRes = R.drawable.placeholder2,
                postCategory = com.geekhaven.alumx.model.PostCategory.ANNOUNCEMENTS,
                tags = listOf("TECH", "CAREER ADVICE", "GOOGLE"),
                fullContent = "Just completed my first year at Google! Here's what I learned about imposter syndrome, team collaboration, and growth.\n\nComing straight from college to one of the biggest tech companies was intimidating. But the supportive team culture and mentorship programs made all the difference.\n\nKey takeaways:\n1. Everyone feels like an imposter sometimes\n2. Ask questions - no one expects you to know everything\n3. Document your wins\n4. Build genuine relationships with your teammates\n\nTo all the new grads joining tech companies - you belong there. Trust the process!"
            ),
            Post(
                authorName = "Rahul Verma",
                authorDescription = "Startup Founder • Class of '18",
                postText = "We just raised our Series A! 🎉 Thank you to everyone who believed in our vision. This is just the beginning...",
                likes = 567,
                comments = 124,
                reposts = 45,
                placeName = "Bangalore, India",
                imageRes = R.drawable.placeholder2,
                postCategory = com.geekhaven.alumx.model.PostCategory.ANNOUNCEMENTS,
                tags = listOf("STARTUP", "FUNDING", "ENTREPRENEURSHIP"),
                fullContent = "We just raised our Series A! 🎉 Thank you to everyone who believed in our vision. This is just the beginning.\n\nStarting a company right after college was the scariest and most rewarding decision of my life. From working out of a tiny apartment to now having a team of 30+ people, it's been an incredible journey.\n\nTo fellow entrepreneurs: The journey is tough, but stay persistent. Find co-founders you trust, listen to your users, and don't be afraid to pivot when needed.\n\nExcited for what's next! 🚀"
            )
        )
    }
}
