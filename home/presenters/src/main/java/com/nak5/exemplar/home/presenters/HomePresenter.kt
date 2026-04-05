package com.nak5.exemplar.home.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.screens.HomeScreen
import com.nak5.exemplar.home.uistate.CircuitStateHolder
import com.nak5.exemplar.home.uistate.HomeUiEvent
import com.nak5.exemplar.home.uistate.HomeUiState
import com.nak5.exemplar.network.NetworkService
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomePresenter @AssistedInject constructor(
    @Assisted private val screen: HomeScreen,
    @Assisted private val navigator: Navigator,
    private val service: NetworkService,
) : Presenter<CircuitStateHolder<HomeUiState>> {

    @Composable
    override fun present(): CircuitStateHolder<HomeUiState> {
        var state by remember { mutableStateOf<HomeUiState>(HomeUiState.Loading) }

        suspend fun refresh() {
            val repos = service.listRepos("nak5ive")
            state = HomeUiState.Loaded(
                items = repos.map { it.name },
            )
        }

        LaunchedEffect(Unit) {
            refresh()
        }

        return CircuitStateHolder(
            state = state,
            onEvent = { event ->
                when (event) {
                    is HomeUiEvent.OnItemClick -> navigator.goTo(DetailScreen(event.name))
                }
            }
        )
    }

    @AssistedFactory
    fun interface Factory {
        fun create(screen: HomeScreen, navigator: Navigator) : HomePresenter
    }
}
