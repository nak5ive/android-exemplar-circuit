package com.nak5.exemplar.home.uistate

import com.slack.circuit.runtime.CircuitUiEvent

sealed interface HomeUiEvent : CircuitUiEvent {
    data class OnItemClick(val name: String) : HomeUiEvent
}