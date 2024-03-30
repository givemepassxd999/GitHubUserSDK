package github.user.sdk.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import github.user.sdk.api.ApiManager
import github.user.sdk.api.ApiService
import github.user.sdk.repo.MainRepository
import github.user.sdk.repo.MainRepositoryImpl
import github.user.sdk.repo.UserDetailRepository
import github.user.sdk.repo.UserDetailRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun providesApiService(): ApiService = ApiManager.create()

    @Provides
    fun providerMainRepository(service: ApiService): MainRepository =
        MainRepositoryImpl(service)

    @Provides
    fun providerUserDetailRepository(service: ApiService): UserDetailRepository =
        UserDetailRepositoryImpl(service)
}
