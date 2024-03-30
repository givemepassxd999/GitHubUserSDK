package github.user.sdk.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import github.user.sdk.api.ApiManager
import github.user.sdk.api.ApiService
import github.user.sdk.repo.Repository
import github.user.sdk.repo.RepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    fun providesApiService(): ApiService = ApiManager.create()

    @Provides
    fun providerRepository(): Repository = RepositoryImpl(ApiManager.create())
}