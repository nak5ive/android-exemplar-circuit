package com.nak5.exemplar.di

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

@ContributesTo(AppScope::class)
interface CircuitProvider {
    @Provides
    fun provideCircuit(
        presenterFactories: Set<Presenter.Factory>,
        uiFactories: Set<Ui.Factory>,
    ) : Circuit = Circuit.Builder()
        .apply {
            presenterFactories.forEach(::addPresenterFactory)
            uiFactories.forEach(::addUiFactory)
        }
        .build()
}