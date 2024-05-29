package dev.takuji31.repository

import dev.takuji31.model.UserId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.security.SecureRandom
import javax.inject.Inject
import kotlin.math.absoluteValue

interface UserAccountRepository {
    val currentUserId: UserId
    fun observeCurrentUserId(): StateFlow<UserId>
    fun resetUser()
}

class UserAccountRepositoryImpl @Inject constructor() : UserAccountRepository {
    private val random = SecureRandom()
    private val _currentUserId = MutableStateFlow(UserId(random.nextLong().absoluteValue))
    override val currentUserId: UserId
        get() = _currentUserId.value

    override fun observeCurrentUserId(): StateFlow<UserId> = _currentUserId.asStateFlow()

    override fun resetUser() {
        _currentUserId.value = UserId(random.nextLong().absoluteValue)
    }
}