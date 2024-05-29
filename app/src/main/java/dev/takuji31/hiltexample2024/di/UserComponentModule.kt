package dev.takuji31.hiltexample2024.di

import dev.takuji31.repository.CounterRepository
import dev.takuji31.repository.CounterRepositoryImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dev.takuji31.component.UserComponent

@Module
@InstallIn(UserComponent::class)
abstract class UserScopeRepositoryBinderModule {
    @Binds
    abstract fun bindCounterRepository(impl: CounterRepositoryImpl): CounterRepository
}