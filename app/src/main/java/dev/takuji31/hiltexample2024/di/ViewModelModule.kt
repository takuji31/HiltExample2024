package dev.takuji31.hiltexample2024.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.takuji31.repository.CounterRepository
import dev.takuji31.repository.ForCurrentUser

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @ForCurrentUser
    @Provides
    fun provideCurrentUserCounterRepository(userComponentManager: UserComponentManager): CounterRepository =
        userComponentManager.counterRepository
}
