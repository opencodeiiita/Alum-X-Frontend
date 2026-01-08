package com.geekhaven.alumx.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.geekhaven.alumx.AlumXScreen
import com.geekhaven.alumx.components.post.PostItem
import com.geekhaven.alumx.model.Post
import com.geekhaven.alumx.presentation.network.NetworkScreen
import com.geekhaven.alumx.ui.theme.DeepBlueBG
import com.geekhaven.alumx.ui.theme.PrimaryBlue
import com.geekhaven.alumx.ui.theme.SurfaceColor

private val items = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        hasNews = true,
    ),
    BottomNavigationItem(
        title = "Network",
        selectedIcon = Icons.Filled.People,
        hasNews = false,
    ),
    BottomNavigationItem(
        title = "Jobs",
        selectedIcon = Icons.Filled.Work,
        hasNews = false,
    ), BottomNavigationItem(
        title = "Alerts",
        selectedIcon = Icons.Filled.Notifications,
        hasNews = false,
        badgeCount = 45
    ),
    BottomNavigationItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        hasNews = false,
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DeepBlueBG
        ),
        title = {
            Column() {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    }

                    OutlinedTextField(
                        value = query,
                        onValueChange = onQueryChange,
                        placeholder = { Text("Search") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f),
                        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = null)
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = SurfaceColor,
                            unfocusedContainerColor = SurfaceColor,
                            unfocusedIndicatorColor = Color.White.copy(alpha = 0.1f)
                        ),
                    )

                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Message, contentDescription = null)
                    }

                }

            }
        },
    )
}

@Composable
fun HomeBottomBar(
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        containerColor = DeepBlueBG,
        contentColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemClick(index) },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = item.selectedIcon,
                            contentDescription = item.title,
                            tint = if (selectedIndex == index) {
                                PrimaryBlue
                            } else {
                                Color.Gray
                            }
                        )
                    }
                },
                label = {
                    Text(
                        item.title,
                        color = if (selectedIndex == index) {
                            PrimaryBlue
                        } else {
                            Color.Gray
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        navController,
        uiState,
        viewModel::onSearchChange,
        viewModel::onBottomNavClick
    )
}

@Composable
fun HomeScreenContent(
    navController: NavController,
    uiState: HomeUiState,
    onSearchChange: (String) -> Unit,
    onBottomNavClick: (Int) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            CreatePostButton({
                navController.navigate(AlumXScreen.CreatePost.name)
            })
        },
        topBar = {
            HomeScreenTopBar(
                query = uiState.searchQuery,
                onQueryChange = onSearchChange
            )
        },
        bottomBar = {
            HomeBottomBar(
                selectedIndex = uiState.selectedBottomIndex,
                onItemClick = onBottomNavClick
            )
        }

    ) { innerPadding ->
        when (uiState.selectedBottomIndex) {
            1 -> NetworkScreen(
                innerPadding = innerPadding,
                searchQuery = uiState.searchQuery
            )
            else -> PostList(Modifier, uiState.posts, innerPadding, navController)
        }
    }
}

@Composable
fun CreatePostButton(onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = PrimaryBlue,
        onClick = onClick,
    ) {
        Icon(Icons.Filled.Add, null)
    }
}

@Composable
fun PostList(modifier: Modifier = Modifier, postList: List<Post>, innerPadding: PaddingValues, navController: NavController) {
    LazyColumn(modifier = modifier, contentPadding = innerPadding) {
        items(postList) { post ->
            PostItem(post, onClick = {
                navController.navigate(AlumXScreen.PostDetail.name)
            })
        }
    }
}


