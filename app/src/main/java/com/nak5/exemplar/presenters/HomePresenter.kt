package com.nak5.exemplar.presenters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.nak5.exemplar.network.NetworkService
import com.nak5.exemplar.screens.DetailScreen
import com.nak5.exemplar.screens.HomeScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class HomePresenter(
    @Assisted private val screen: HomeScreen,
    @Assisted private val navigator: Navigator,
    private val service: NetworkService,
) : Presenter<HomeScreen.State> {
    @Composable
    override fun present(): HomeScreen.State {
        LaunchedEffect(Unit) {
            val repos = service.listRepos("nak5ive")
            println(repos)
        }

        return HomeScreen.State(
            items = listOf("Joe", "Bob", "Alice"),
            onEvent = { event ->
                when (event) {
                    is HomeScreen.Event.OnItemClick -> navigator.goTo(DetailScreen(event.name))
                }
            }
        )
    }

    @AssistedFactory
    fun interface Factory {
        fun create(screen: HomeScreen, navigator: Navigator) : HomePresenter
    }
}