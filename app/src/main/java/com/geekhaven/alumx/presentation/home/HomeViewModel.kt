package com.geekhaven.alumx.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekhaven.alumx.data.repository.PostRepository
import com.geekhaven.alumx.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = postRepository.getPosts()
            result.onSuccess { posts ->
                _uiState.update { it.copy(posts = posts, isLoading = false) }
            }.onFailure {
                // Handle error (optionally add error message to state)
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onSearchChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        // Optional: Trigger API search here with debounce
    }

    fun onBottomNavClick(index: Int) {
        _uiState.update { it.copy(selectedBottomIndex = index) }
    }
}