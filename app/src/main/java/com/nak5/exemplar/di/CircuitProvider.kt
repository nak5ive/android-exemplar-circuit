package com.nak5.exemplar.di

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CircuitModule {
    @Provides
    fun provideCircuit(
        presenterFactories: Set<@JvmSuppressWildcards Presenter.Factory>,
        uiFactories: Set<@JvmSuppressWildcards Ui.Factory>,
    ): Circuit = Circuit.Builder()
        .apply {
            presenterFactories.forEach(::addPresenterFactory)
            uiFactories.forEach(::addUiFactory)
        }
        .build()
}