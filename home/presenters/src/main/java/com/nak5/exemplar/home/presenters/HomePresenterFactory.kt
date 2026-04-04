package com.nak5.exemplar.home.presenters

import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.screens.HomeScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.IntoSet

@ContributesTo(AppScope::class)
interface HomePresenterBindings {

    @Binds
    @IntoSet
    fun provideHomePresenterFactory(factory: HomePresenterFactory): Presenter.Factory
}

@Inject
class HomePresenterFactory(
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