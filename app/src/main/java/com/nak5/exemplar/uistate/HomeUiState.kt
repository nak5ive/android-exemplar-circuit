package com.nak5.exemplar.uistate

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Loaded(
        val items: List<String>,
    ) : HomeUiState
}

