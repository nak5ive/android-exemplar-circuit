package com.nak5.exemplar.home.uistate

import androidx.compose.runtime.Stable
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Loaded(
        val items: List<String>,
    ) : HomeUiState
}

@Stable
data class CircuitStateHolder<T>(
    val state: T,
    val onEvent: (CircuitUiEvent) -> Unit,
): CircuitUiState
