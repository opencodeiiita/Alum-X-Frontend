package com.geekhaven.alumx.model
import com.geekhaven.alumx.R

data class Post(
    val authorName: String,
    val authorDescription: String,
    val postText: String,
    val likes: Int,
    val comments: Int,
    val reposts: Int,
    val placeName: String,
    val imageRes: Int,
    val profileRes: Int = R.drawable.ic_launcher_foreground,
    val postCategory: PostCategory,
    val title: String? = null,  
    val tags: List<String> = emptyList(),  
    val fullContent: String = postText  
)
 