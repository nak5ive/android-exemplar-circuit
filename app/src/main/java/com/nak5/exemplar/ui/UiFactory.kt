package com.nak5.exemplar.ui

import com.nak5.exemplar.screens.DetailScreen
import com.nak5.exemplar.screens.HomeScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import dev.zacsweers.metro.Inject

@Inject
class UiFactory : Ui.Factory {
    override fun create(
        screen: Screen,
        context: CircuitContext
    ): Ui<*>? {
        return when (screen) {
            is HomeScreen -> ui<HomeScreen.State> { state, modifier -> HomeUi(state, modifier) }
            is DetailScreen -> ui<DetailScreen.State> { state, modifier -> DetailUi(state, modifier) }
            else -> null
        }
    }
}