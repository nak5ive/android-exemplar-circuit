package com.nak5.exemplar.home.ui

import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.screens.HomeScreen
import com.nak5.exemplar.home.uistate.DetailUiState
import com.nak5.exemplar.home.uistate.HomeUiState
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.IntoSet

@ContributesTo(AppScope::class)
interface HomeUiBindings {

    @Binds
    @IntoSet
    fun bindHomeUiFactory(factory: HomeUiFactory): Ui.Factory
}

@Inject
class HomeUiFactory : Ui.Factory {
    override fun create(
        screen: Screen,
        context: CircuitContext
    ): Ui<*>? {
        return when (screen) {
            is HomeScreen -> ui<HomeUiState> { state, modifier -> HomeUi(state, modifier) }
            is DetailScreen -> ui<DetailUiState> { state, modifier -> DetailUi(state, modifier) }
            else -> null
        }
    }
}