package github.user.sdk.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.util.MainCoroutineRule
import github.user.sdk.repo.UserDetailRepository
import github.user.sdk.util.TestUserDetailGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class UserDetailViewModelTest {
    private lateinit var userDetailViewModel: UserDetailViewModel
    private val userDetailRepository: UserDetailRepository = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testUserDetailGenerator = TestUserDetailGenerator()

    @Test
    fun fetchUserDetailTest() {
        val mockUserDetail = testUserDetailGenerator.generateUser()
        coEvery { userDetailRepository.getUserDetail("") } returns flow {
            emit(mockUserDetail)
        }
        userDetailViewModel = UserDetailViewModel(userDetailRepository)
        userDetailViewModel.fetchUserDetail("")
        val userDetail = userDetailViewModel.user.value
        Assert.assertEquals(mockUserDetail, userDetail)
    }
}