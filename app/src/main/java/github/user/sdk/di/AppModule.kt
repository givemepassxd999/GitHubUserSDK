package github.user.sdk.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import github.user.sdk.api.ApiManager
import github.user.sdk.api.ApiService
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {
    @Provides
    fun providerApiManager() = ApiManager.create()
}

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    fun providesApiService(): ApiService = ApiManager.create()
}