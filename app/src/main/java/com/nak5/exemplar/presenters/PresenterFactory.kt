package com.nak5.exemplar.presenters

import com.nak5.exemplar.screens.DetailScreen
import com.nak5.exemplar.screens.HomeScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dev.zacsweers.metro.Inject

@Inject
class PresenterFactory(
    private val homePresenterFactory: HomePresenter.Factory,
    private val detailPresenterFactory: DetailPresenter.Factory,
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is HomeScreen -> homePresenterFactory.create(screen, navigator)
            is DetailScreen -> detailPresenterFactory.create(screen, navigator)
            else -> null
        }
    }
}