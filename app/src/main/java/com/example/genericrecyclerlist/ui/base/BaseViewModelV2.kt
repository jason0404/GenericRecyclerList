package com.example.genericrecyclerlist.ui.base

import androidx.lifecycle.ViewModel
import com.example.genericrecyclerlist.ui.recycler.adapter.Contract.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModelV2: ViewModel() {
    private val initialState: State by lazy { createInitialState() }

    abstract fun createInitialState(): State
    abstract fun subscribeEvent()

    val currentState: State
        get() = initialState

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    // Create MutableStateFlow to be subscribe
    val uiState: StateFlow<State>
        get() = _uiState.asStateFlow()

    // Shared Flow is a hot flow and will emit value whenever the producer produce the value
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event>
        get() = _event

    private val _effect: Channel<Effect> = Channel()
    val effect: Flow<Effect>
        get() = _effect.receiveAsFlow()
}