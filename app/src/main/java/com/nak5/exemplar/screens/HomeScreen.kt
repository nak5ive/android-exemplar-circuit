package com.nak5.exemplar.screens

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeScreen : Screen {
    data class State(
        val items: List<String>,
        val onEvent: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data class OnItemClick(val name: String) : Event
    }
}
