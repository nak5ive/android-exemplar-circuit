package com.nak5.exemplar.network

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@ContributesTo(AppScope::class)
interface ServiceProvider {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): NetworkService = retrofit.create<NetworkService>()
}