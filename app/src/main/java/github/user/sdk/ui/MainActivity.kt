package github.user.sdk.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import github.user.sdk.theme.GitHubUserSDKTheme
import github.user.sdk.viewmodel.MainViewModel

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubUserSDKTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    LaunchedEffect(key1 = Unit) {
                        viewModel.fetchUsers()
                    }
                    val keyboardController = LocalSoftwareKeyboardController.current
                    val searchQuery = viewModel.searchQuery.collectAsState().value
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { viewModel.onSearchQueryChange(it) },
                        onSearch = {
                            //to do search
                            keyboardController?.hide()
                        },
                        placeholder = {
                            Text(text = "Search github users")
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
                                        contentDescription = "Clear search"
                                    )
                                }
                            }
                        },
                        content = {},
                        active = true,
                        onActiveChange = {},
                        tonalElevation = 0.dp
                    )
                }
            }
        }
    }
}