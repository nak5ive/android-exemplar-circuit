package com.nak5.exemplar.home.presenters

import androidx.compose.runtime.Composable
import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.uistate.DetailUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

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

    @AssistedFactory
    fun interface Factory {
        fun create(screen: DetailScreen, navigator: Navigator) : DetailPresenter
    }
}