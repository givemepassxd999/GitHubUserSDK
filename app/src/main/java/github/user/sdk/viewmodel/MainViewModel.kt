package github.user.sdk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.user.sdk.data.UserResponse
import github.user.sdk.repo.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _users = MutableStateFlow(emptyList<UserResponse>())
    val users = _users.asStateFlow()

    private val _userClick = MutableStateFlow(UserResponse())
    val userClick = _userClick.asStateFlow()

    private val _originUsers = MutableStateFlow(emptyList<UserResponse>())

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        if (query.isEmpty()) {
            _users.value = _originUsers.value
        } else {
            _users.value = _originUsers.value.filter {
                it.login?.contains(query, true) == true
            }
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            repository.queryUsers().collect {
                _originUsers.value = it
                _users.value = it
            }
        }
    }

    fun onUserClick(user: UserResponse) {
        _userClick.value = user
    }
}