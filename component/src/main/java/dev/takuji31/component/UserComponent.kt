package dev.takuji31.component

import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent
import dev.takuji31.model.UserId
import javax.inject.Scope

@UserScope
@DefineComponent(parent = SingletonComponent::class)
interface UserComponent {
    @DefineComponent.Builder
    interface Builder {
        fun userId(@BindsInstance userId: UserId): Builder
        fun build(): UserComponent
    }
}

@Scope
annotation class UserScope