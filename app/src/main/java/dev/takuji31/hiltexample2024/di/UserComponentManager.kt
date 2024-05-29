package dev.takuji31.hiltexample2024.di

import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dev.takuji31.component.UserComponent
import dev.takuji31.repository.CounterRepository
import dev.takuji31.repository.UserAccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.skip
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class UserComponentManager @Inject constructor(
    private val scope: CoroutineScope,
    private val componentBuilder: Provider<UserComponent.Builder>,
    private val userAccountRepository: UserAccountRepository,
) {
    @EntryPoint
    @InstallIn(UserComponent::class)
    interface UserComponentEntryPoint {
        fun counterRepository(): CounterRepository
    }

    private var component: UserComponent = componentBuilder.get().bindUserId(userAccountRepository.currentUserId).build()

    init {
        scope.launch {
            userAccountRepository.observeCurrentUserId()
                .drop(1)
                .collect {
                component = componentBuilder.get().bindUserId(it).build()
            }
        }
    }

    private val entryPoint get() = EntryPoints.get(component, UserComponentEntryPoint::class.java)

    val counterRepository: CounterRepository get() = entryPoint.counterRepository()
}