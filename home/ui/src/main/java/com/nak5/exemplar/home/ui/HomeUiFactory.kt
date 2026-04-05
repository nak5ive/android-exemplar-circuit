package com.nak5.exemplar.home.ui

import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.screens.HomeScreen
import com.nak5.exemplar.home.uistate.CircuitStateHolder
import com.nak5.exemplar.home.uistate.DetailUiState
import com.nak5.exemplar.home.uistate.HomeUiState
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
interface HomeUiBindingsModule {

    @Binds
    @IntoSet
    fun bindHomeUiFactory(factory: HomeUiFactory): Ui.Factory
}

class HomeUiFactory @Inject constructor() : Ui.Factory {
    override fun create(
        screen: Screen,
        context: CircuitContext
    ): Ui<*>? {
        return when (screen) {
            is HomeScreen -> ui<CircuitStateHolder<HomeUiState>> { state, modifier -> HomeUi(state, modifier) }
            is DetailScreen -> ui<DetailUiState> { state, modifier -> DetailUi(state, modifier) }
            else -> null
        }
    }
}