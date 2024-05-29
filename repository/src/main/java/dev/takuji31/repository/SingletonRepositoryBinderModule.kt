package dev.takuji31.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonRepositoryBinderModule {
    @Binds
    @Singleton
    abstract fun userAccountRepository(repository: UserAccountRepositoryImpl): UserAccountRepository

    @Binds
    @Singleton
    @ForAllUser
    abstract fun counterRepository(repository: CounterRepositoryImpl): CounterRepository
}