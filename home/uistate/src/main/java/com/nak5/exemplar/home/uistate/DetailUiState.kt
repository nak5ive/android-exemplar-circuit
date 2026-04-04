package com.nak5.exemplar.home.uistate

import com.slack.circuit.runtime.CircuitUiState

data class DetailUiState(
    val name: String,
    val onEvent: (DetailUiEvent) -> Unit,
) : CircuitUiState
