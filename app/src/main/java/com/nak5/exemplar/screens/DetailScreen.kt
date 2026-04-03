package com.nak5.exemplar.screens

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailScreen(
    val name: String,
) : Screen {
    data class State(
        val name: String,
        val onEvent: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent
}
