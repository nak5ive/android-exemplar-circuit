package com.nak5.exemplar.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.nak5.exemplar.screens.DetailScreen
import com.nak5.exemplar.screens.HomeScreen
import com.nak5.exemplar.network.NetworkService
import com.nak5.exemplar.uistate.util.StateHolder
import com.nak5.exemplar.uistate.HomeUiEvent
import com.nak5.exemplar.uistate.HomeUiState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent

class HomePresenter @AssistedInject constructor(
    @Assisted private val screen: HomeScreen,
    @Assisted private val navigator: Navigator,
    private val service: NetworkService,
) : Presenter<StateHolder<HomeUiState, HomeUiEvent>> {

    @Composable
    override fun present(): StateHolder<HomeUiState, HomeUiEvent> {
        var state by rememberRetained { mutableStateOf<HomeUiState>(HomeUiState.Loading) }

        suspend fun refresh() {
            val repos = service.listRepos("nak5ive")
            state = HomeUiState.Loaded(
                items = repos.map { it.name },
            )
        }

        if (state is HomeUiState.Loading) {
            LaunchedEffect(Unit) {
                refresh()
            }
        }

        return StateHolder(
            state = state,
            onEvent = { event ->
                when (event) {
                    is HomeUiEvent.OnItemClick -> navigator.goTo(DetailScreen(event.name))
                }
            }
        )
    }

    @CircuitInject(HomeScreen::class, SingletonComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(screen: HomeScreen, navigator: Navigator) : HomePresenter
    }
}
