package com.nak5.exemplar.home.presenters

import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.screens.HomeScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
interface HomePresenterBindingsModule {

    @Binds
    @IntoSet
    fun provideHomePresenterFactory(factory: HomePresenterFactory): Presenter.Factory
}

class HomePresenterFactory @Inject constructor(
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