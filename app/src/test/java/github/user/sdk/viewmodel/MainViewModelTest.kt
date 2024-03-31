package github.user.sdk.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import com.util.TestModelsGenerator
import github.user.sdk.repo.MainRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private val mainRepository: MainRepository = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Test
    fun fetchUserTest() {
        val mockUsers = testModelsGenerator.generateUsers()
        coEvery { mainRepository.queryUsers() } returns flow {
            emit(mockUsers)
        }
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.fetchUsers()
        val users = mainViewModel.users.value
        Assert.assertEquals(mockUsers, users)
    }

    @Test
    fun onSearchQueryChangeTest() {
        val mockUsers = testModelsGenerator.generateUsers()
        coEvery { mainRepository.queryUsers() } returns flow {
            emit(mockUsers)
        }
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.fetchUsers()
        //for empty query testing
        val emptyQuery = ""
        mainViewModel.onSearchQueryChange(emptyQuery)
        Assert.assertEquals(mockUsers.size, mainViewModel.users.value.size)
        //for search query testing
        val query = "test"
        mainViewModel.onSearchQueryChange(query)
        Assert.assertEquals(query, mainViewModel.searchQuery.value)
        //for filter testing
        val mockUsersFilter = mockUsers.filter {
            it.login?.contains(query, true) == true
        }
        Assert.assertEquals(mockUsersFilter.size, mainViewModel.users.value.size)
    }

    @Test
    fun onUserClickTest() {
        val mockUser = testModelsGenerator.generateAUser()
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.onUserClick(mockUser)
        Assert.assertEquals(mockUser, mainViewModel.userClick.value)
    }
}