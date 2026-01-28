package com.geekhaven.alumx.model

import android.net.Uri
import com.geekhaven.alumx.R

data class DraftPost(
    val authorName: String="User",
    val authorDescription: String="Chad",
    val profileRes: Int = R.drawable.ic_launcher_foreground,
    val postText: String = "",
    val postCategory: PostCategory = PostCategory.JOBS,
    val imageUri: Uri? = null,
    val fileUri: Uri? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
