package com.nak5.exemplar.home.presenters

import androidx.compose.runtime.Composable
import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.uistate.DetailUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class DetailPresenter(
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