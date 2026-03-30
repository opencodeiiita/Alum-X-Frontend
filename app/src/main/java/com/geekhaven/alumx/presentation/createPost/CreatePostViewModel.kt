package com.geekhaven.alumx.presentation.createPost

import android.net.Uri
import android.util.Log // Import Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekhaven.alumx.data.repository.PostRepository
import com.geekhaven.alumx.model.PostCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CreatePostViewModel"

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreatePostUIState())
    val uiState: StateFlow<CreatePostUIState> = _uiState.asStateFlow()

    private val _postResult = MutableStateFlow<Result<Unit>?>(null)
    val postResult: StateFlow<Result<Unit>?> = _postResult.asStateFlow()

    fun onTextChange(postText: String) {
        _uiState.update {
            it.copy(draftPost = it.draftPost.copy(postText = postText))
        }
    }

    fun onCategorySelected(postCategory: PostCategory) {
        _uiState.update {
            it.copy(draftPost = it.draftPost.copy(postCategory = postCategory))
        }
    }

    fun onImageSelected(uri: Uri?) {
        _uiState.update {
            it.copy(draftPost = it.draftPost.copy(imageUri = uri))
        }
    }

    fun createPost() {
        val description = uiState.value.draftPost.postText
        Log.d(TAG, "createPost called. Description length: ${description.length}")

        if (description.isBlank()) {
            Log.w(TAG, "Post description is empty, aborting creation.")
            return
        }

        viewModelScope.launch {
            Log.d(TAG, "Launching repository call...")
            val result = postRepository.createPost(description)
            result.onSuccess {
                Log.d(TAG, "Repository returned success")
            }.onFailure {
                Log.e(TAG, "Repository returned failure: ${it.message}")
            }
            _postResult.value = result
        }
    }
}