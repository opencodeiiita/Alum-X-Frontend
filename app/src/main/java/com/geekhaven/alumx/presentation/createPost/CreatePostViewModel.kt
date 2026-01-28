package com.geekhaven.alumx.presentation.createPost

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.geekhaven.alumx.model.PostCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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


    private val repository = com.geekhaven.alumx.data.repository.JobPostRepository()

    fun onCategorySelected(postCategory: PostCategory) {
        _uiState.update {
            it.copy(
                draftPost = it.draftPost.copy(
                    postCategory = postCategory
                )
            )
        }
    }

    fun createPost() {
        val currentDraft = _uiState.value.draftPost
        if (currentDraft.postText.isBlank()) return

        _uiState.update {
            it.copy(draftPost = it.draftPost.copy(isLoading = true, errorMessage = null))
        }

        // TODO: Move to viewModelScope when DI is set up
        GlobalScope.launch {
            val result = repository.createJobPost(
                username = currentDraft.authorName, // Using authorName as username for now
                description = currentDraft.postText,
                imageUrls = emptyList() // TODO: Handle image upload
            )

            _uiState.update {
                if (result.isSuccess) {
                    it.copy(
                        draftPost = it.draftPost.copy(
                            isLoading = false,
                            isSuccess = true,
                            postText = "" // Reset after success
                        )
                    )
                } else {
                    it.copy(
                        draftPost = it.draftPost.copy(
                            isLoading = false,
                            errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                        )
                    )
                }
            }
        }
    }

    fun resetState() {
        _uiState.update {
            it.copy(
                draftPost = it.draftPost.copy(
                    isSuccess = false,
                    errorMessage = null
                )
            )
        }
    }
}