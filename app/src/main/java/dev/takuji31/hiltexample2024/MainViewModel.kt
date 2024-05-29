package dev.takuji31.hiltexample2024

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.takuji31.repository.CounterRepository
import dev.takuji31.repository.ForAllUser
import dev.takuji31.repository.ForCurrentUser
import dev.takuji31.repository.UserAccountRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userAccountRepository: UserAccountRepository,
    @param:ForCurrentUser private val currentUserCounterRepository: CounterRepository,
    @param:ForAllUser private val allUserCounterRepository: CounterRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
}