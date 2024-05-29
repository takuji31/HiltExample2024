package dev.takuji31.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface CounterRepository {
    fun increment()
    fun observeCount(): StateFlow<Int>
}

class CounterRepositoryImpl @Inject constructor() : CounterRepository {
    private val _count = MutableStateFlow(0)
    override fun increment() {
        _count.update { it + 1 }
    }

    override fun observeCount(): StateFlow<Int> = _count.asStateFlow()
}