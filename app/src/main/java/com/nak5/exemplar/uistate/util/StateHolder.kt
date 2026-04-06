package com.nak5.exemplar.uistate.util

import androidx.compose.runtime.Stable
import com.slack.circuit.runtime.CircuitUiState

@Stable
data class StateHolder<UiState, UiEvent>(
    val state: UiState,
    val onEvent: (UiEvent) -> Unit,
): CircuitUiState