package com.geekhaven.alumx.presentation.createPost

import android.net.Uri
import android.util.Log // Import Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.geekhaven.alumx.AlumXScreen
import com.geekhaven.alumx.model.PostCategory
import com.geekhaven.alumx.ui.theme.*

private const val TAG = "CreatePostScreen"

@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val postResult by viewModel.postResult.collectAsStateWithLifecycle()

    // Handle Success Navigation
    LaunchedEffect(postResult) {
        postResult?.onSuccess {
            Log.d(TAG, "Post creation successful, navigating back")
            navController.navigate(AlumXScreen.Home.name) {
                popUpTo(AlumXScreen.Home.name) { inclusive = true }
            }
        }
        postResult?.onFailure {
            Log.e(TAG, "Post creation failed in UI effect: ${it.message}")
        }
    }

    CreatePostScreenContent(
        uiState = uiState,
        onTextChange = viewModel::onTextChange,
        navController = navController,
        onCategorySelected = viewModel::onCategorySelected,
        onImageSelected = viewModel::onImageSelected,
        onPostClick = {
            Log.d(TAG, "Post button clicked in UI") // LOG ADDED HERE
            viewModel.createPost()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostTopAppBar(onCrossClick: () -> Unit, onPostClick: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepBlueBG),
        title = { Text(text = "Create Post") },
        navigationIcon = {
            IconButton(onClick = onCrossClick) {
                Icon(Icons.Default.Close, contentDescription = null)
            }
        },
        actions = {
            TextButton(
                onClick = onPostClick // ENSURE THIS IS CONNECTED
            ) {
                Text(text = "Post", color = PrimaryBlue)
            }
        }
    )
}

@Composable
fun createPostBottomBar(
    photoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    filePickerLauncher: ManagedActivityResultLauncher<Array<String>, Uri?>
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        IconButton(onClick = {
            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }) { Icon(Icons.Default.Image, contentDescription = null) }

        IconButton(onClick = {
            filePickerLauncher.launch(arrayOf("*/*"))
        }) { Icon(Icons.Default.UploadFile, contentDescription = null) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCategoryChips(
    selectedCategory: PostCategory,
    onCategorySelected: (PostCategory) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(PostCategory.values()) { category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = { onCategorySelected(category) },
                label = { Text(category.label) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = PrimaryBlue,
                    selectedLabelColor = AppWhite
                )
            )
        }
    }
}

@Composable
fun CreatePostScreenContent(
    uiState: CreatePostUIState,
    onTextChange: (postText: String) -> Unit,
    navController: NavController,
    onCategorySelected: (category: PostCategory) -> Unit,
    onImageSelected: (uri: Uri?) -> Unit,
    onPostClick: () -> Unit
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> onImageSelected(uri) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri -> }

    Scaffold(
        topBar = {
            CreatePostTopAppBar(
                onCrossClick = { navController.navigate(AlumXScreen.Home.name) },
                onPostClick = onPostClick
            )
        },
        bottomBar = { createPostBottomBar(photoPickerLauncher, filePickerLauncher) }
    ) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding).padding(horizontal = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(5.dp))
                Image(
                    painter = painterResource(id = uiState.draftPost.profileRes),
                    contentDescription = "Profile picture",
                    modifier = Modifier.size(42.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = uiState.draftPost.authorName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = uiState.draftPost.authorDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.padding(start = 5.dp),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Color.Gray,
                text = "Post Category"
            )
            PostCategoryChips(
                selectedCategory = uiState.draftPost.postCategory,
                onCategorySelected = onCategorySelected
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = uiState.draftPost.postText,
                onValueChange = onTextChange,
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text("Description") },
                modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SurfaceColor,
                    unfocusedContainerColor = SurfaceColor,
                    unfocusedIndicatorColor = Color.White.copy(alpha = 0.1f)
                )
            )
        }
    }
}