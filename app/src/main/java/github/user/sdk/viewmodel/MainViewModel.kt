package github.user.sdk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.user.sdk.data.UserResponse
import github.user.sdk.repo.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _users = MutableStateFlow(emptyList<UserResponse>())
    val users = _users.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    // fetch users
    fun fetchUsers() {
        viewModelScope.launch {
            repository.queryUsers().collect {
                Timber.d("fetchUsers: $it")
                _users.value = it
            }
        }
    }
}