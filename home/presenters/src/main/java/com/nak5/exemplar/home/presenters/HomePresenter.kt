package com.nak5.exemplar.home.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.screens.HomeScreen
import com.nak5.exemplar.home.uistate.HomeUiEvent
import com.nak5.exemplar.home.uistate.HomeUiState
import com.nak5.exemplar.network.NetworkService
import com.nak5.exemplar.network.data.Repo
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import kotlinx.coroutines.flow.Flow

@AssistedInject
class HomePresenter(
    @Assisted private val screen: HomeScreen,
    @Assisted private val navigator: Navigator,
    private val service: NetworkService,
) : Presenter<HomeUiState> {

    @Composable
    override fun present(): HomeUiState {
        var repos by rememberSaveable { mutableStateOf<List<Repo>?>(null) }

        suspend fun refresh() {
            repos = service.listRepos("nak5ive")
        }

        if (repos == null) {
            LaunchedEffect(Unit) {
                refresh()
            }
        }

        fun onEvent(event: HomeUiEvent) {
            when (event) {
                is HomeUiEvent.OnItemClick -> navigator.goTo(DetailScreen(event.name))
            }
        }

        return if (repos == null) {
            HomeUiState.Loading
        } else {
            HomeUiState.Loaded(
                items = repos!!.map { it.name },
                onEvent = ::onEvent
            )
        }
    }

    @AssistedFactory
    fun interface Factory {
        fun create(screen: HomeScreen, navigator: Navigator) : HomePresenter
    }
}
