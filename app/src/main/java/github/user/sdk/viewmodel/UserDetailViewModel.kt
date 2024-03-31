package github.user.sdk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.user.sdk.data.UserDetailResponse
import github.user.sdk.repo.UserDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel(private val userDetailRepository: UserDetailRepository) :
    ViewModel() {

    private val _userDetail = MutableStateFlow(UserDetailResponse())
    val user = _userDetail.asStateFlow()

    fun fetchUserDetail(userName: String) {
        viewModelScope.launch {
            userDetailRepository.getUserDetail(userName).collect { userDetailInfo ->
                _userDetail.value = userDetailInfo
            }
        }
    }
}