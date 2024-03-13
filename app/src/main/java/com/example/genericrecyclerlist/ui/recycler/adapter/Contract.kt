package com.example.genericrecyclerlist.ui.recycler.adapter

class Contract {
    sealed class Event: UiEvent {
        data object OnButtonClicked: Event()
    }

    data class State(
        val homeState: HomeState
    )

    sealed class Effect: UIEffect {
        data object ShowLoadingDialog: Effect()
        data object ShowToast: Effect()
    }
}

interface UiEvent
interface HomeState
interface UIEffect