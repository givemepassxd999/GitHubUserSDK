package github.user.sdk.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import github.user.sdk.R
import github.user.sdk.data.UserResponse
import github.user.sdk.theme.GitHubUserSDKTheme
import github.user.sdk.ui.UserDetailFragment.Companion.USER_DETAIL_TAG
import github.user.sdk.viewmodel.MainViewModel

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubUserSDKTheme {
                Surface(
                    color = Color.White,
                ) {
                    LaunchedEffect(key1 = Unit) {
                        viewModel.fetchUsers()
                    }
                    val keyboardController = LocalSoftwareKeyboardController.current
                    val searchQuery = viewModel.searchQuery.collectAsState().value
                    val userClick = viewModel.userClick.collectAsState().value
                    userClick.id?.let {
                        UserDetailFragment.getInstance(userClick)
                            .show(supportFragmentManager, USER_DETAIL_TAG)
                        viewModel.onUserClick(UserResponse())
                    }
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { viewModel.onSearchQueryChange(it) },
                        onSearch = {
                            keyboardController?.hide()
                        },
                        placeholder = {
                            Text(text = getString(R.string.search_github_users))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                tint = MaterialTheme.colorScheme.onSurface,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        tint = MaterialTheme.colorScheme.onSurface,
                                        contentDescription = getString(R.string.clear_search)
                                    )
                                }
                            }
                        },
                        content = {
                            UserListView()
                        },
                        active = true,
                        onActiveChange = {},
                        tonalElevation = 0.dp
                    )
                }
            }
        }
    }

    @Composable
    private fun UserListView() {
        val users = viewModel.users.collectAsState().value
        if (users.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(
                    text = getString(R.string.no_users_found),
                    color = Color.Black
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                items(users) { user ->
                    Card(elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                        border = BorderStroke(1.dp, Color.LightGray),
                        modifier = Modifier
                            .clickable {
                                viewModel.onUserClick(user = user)
                            }
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)) {
                        UserListItem(user = user)
                    }
                }
            }
        }
    }

    @Composable
    private fun UserListItem(user: UserResponse) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = Color.White
                )
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(start = 10.dp)
                    .size(50.dp)
                    .clip(CircleShape),
                model = user.avatarUrl ?: "",
                contentDescription = null,
            )
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = user.login ?: "", color = Color.Black
                )
                if (user.siteAdmin == true) {
                    Box(
                        modifier = Modifier.background(
                            color = colorResource(
                                id = R.color.color_4953d4
                            ), shape = RoundedCornerShape(10.dp)
                        )
                    ) {
                        Text(
                            text = getString(R.string.staff),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(
                                vertical = 2.dp, horizontal = 10.dp
                            ),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}