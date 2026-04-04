package com.nak5.exemplar.home.uistate

import com.slack.circuit.runtime.CircuitUiState

sealed interface HomeUiState : CircuitUiState {

    data object Loading : HomeUiState

    data class Loaded(
        val items: List<String>,
        val onEvent: (HomeUiEvent) -> Unit = {},
    ) : HomeUiState
}
