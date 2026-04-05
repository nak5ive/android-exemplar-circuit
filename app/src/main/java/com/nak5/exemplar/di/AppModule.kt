package com.nak5.exemplar.di

import com.nak5.exemplar.network.NetworkService
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCircuit(
        presenters: Set<@JvmSuppressWildcards Presenter.Factory>,
        uis: Set<@JvmSuppressWildcards Ui.Factory>,
    ): Circuit = Circuit.Builder()
        .apply {
            presenters.forEach(::addPresenterFactory)
            uis.forEach(::addUiFactory)
        }
        .build()

    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): NetworkService = retrofit.create<NetworkService>()
}