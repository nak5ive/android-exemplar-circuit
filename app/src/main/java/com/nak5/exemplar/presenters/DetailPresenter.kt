package com.nak5.exemplar.presenters

import androidx.compose.runtime.Composable
import com.nak5.exemplar.screens.DetailScreen
import com.nak5.exemplar.uistate.DetailUiState
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
) : Presenter<DetailUiState> {

    @Composable
    override fun present(): DetailUiState {
        return DetailUiState(
            name = screen.name,
            onEvent = {},
        )
    }

    @CircuitInject(DetailScreen::class, SingletonComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(screen: DetailScreen, navigator: Navigator) : DetailPresenter
    }
}