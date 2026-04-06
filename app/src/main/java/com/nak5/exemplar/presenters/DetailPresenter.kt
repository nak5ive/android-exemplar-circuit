package com.nak5.exemplar.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.nak5.exemplar.screens.DetailScreen
import com.nak5.exemplar.uistate.DetailUiEvent
import com.nak5.exemplar.uistate.DetailUiState
import com.nak5.exemplar.uistate.util.StateHolder
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent

class DetailPresenter @AssistedInject constructor(
    @Assisted private val screen: DetailScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<StateHolder<DetailUiState, DetailUiEvent>> {

    @Composable
    override fun present(): StateHolder<DetailUiState, DetailUiEvent> {
        val state = remember { DetailUiState(screen.name) }

        return StateHolder(
            state = state,
            onEvent = {},
        )
    }

    @CircuitInject(DetailScreen::class, SingletonComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(screen: DetailScreen, navigator: Navigator) : DetailPresenter
    }
}