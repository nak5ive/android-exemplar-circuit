package com.nak5.exemplar.core

import com.nak5.exemplar.presenters.PresenterFactory
import com.nak5.exemplar.ui.UiFactory
import com.slack.circuit.foundation.Circuit
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

@ContributesTo(AppScope::class)
interface CircuitProvider {
    @Provides
    fun provideCircuit(
        presenterFactory: PresenterFactory,
        uiFactory: UiFactory,
    ) : Circuit = Circuit.Builder()
        .addPresenterFactory(presenterFactory)
        .addUiFactory(uiFactory)
        .build()
}