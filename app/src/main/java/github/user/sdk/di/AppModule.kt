package github.user.sdk.di

import github.user.sdk.api.ApiManager
import github.user.sdk.repo.MainRepository
import github.user.sdk.repo.MainRepositoryImpl
import github.user.sdk.repo.UserDetailRepository
import github.user.sdk.repo.UserDetailRepositoryImpl
import github.user.sdk.viewmodel.MainViewModel
import github.user.sdk.viewmodel.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AppModule {

    val appModule = module {
        single {
            ApiManager.create()
        }
    }
    val vmModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { UserDetailViewModel(get()) }
    }

    val repoModule = module {
        single<MainRepository> { MainRepositoryImpl(get()) }
        single<UserDetailRepository> { UserDetailRepositoryImpl(get()) }
    }
}
