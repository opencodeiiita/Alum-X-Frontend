package com.geekhaven.alumx.presentation.createPost

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.geekhaven.alumx.model.PostCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CreatePostViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreatePostUIState())
    val uiState: StateFlow<CreatePostUIState> = _uiState.asStateFlow()

    fun onTextChange(postText: String) {
        _uiState.update {
            it.copy(
                draftPost = it.draftPost.copy(
                    postText = postText
                )
            )
        }
    }

    fun onImageSelected(uri: Uri?) {
        _uiState.update {
            it.copy(
                draftPost = it.draftPost.copy(imageUri = uri)
            )
        }
    }



    fun onCategorySelected(postCategory: PostCategory) {
        _uiState.update {
            it.copy(
                draftPost = it.draftPost.copy(
                    postCategory = postCategory
                )
            )
        }
    }
}