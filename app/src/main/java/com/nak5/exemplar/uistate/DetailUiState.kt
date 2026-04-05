package com.nak5.exemplar.uistate

import com.slack.circuit.runtime.CircuitUiState

data class DetailUiState(
    val name: String,
    val onEvent: (DetailUiEvent) -> Unit,
) : CircuitUiState
