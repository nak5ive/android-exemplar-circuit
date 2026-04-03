package com.nak5.exemplar.presenters

import androidx.compose.runtime.Composable
import com.nak5.exemplar.screens.DetailScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class DetailPresenter(
    @Assisted private val screen: DetailScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<DetailScreen.State> {

    @Composable
    override fun present(): DetailScreen.State {
        return DetailScreen.State(
            name = screen.name,
            onEvent = {},
        )
    }

    @AssistedFactory
    fun interface Factory {
        fun create(screen: DetailScreen, navigator: Navigator) : DetailPresenter
    }
}