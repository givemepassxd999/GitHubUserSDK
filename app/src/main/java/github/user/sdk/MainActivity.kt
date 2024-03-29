package github.user.sdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import github.user.sdk.ui.theme.GitHubUserSDKTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubUserSDKTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {

                    SearchBar(
                        query = "",
                        onQueryChange = { },
                        onSearch = {},
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
                        trailingIcon = {},
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