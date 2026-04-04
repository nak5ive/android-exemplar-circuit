package com.nak5.exemplar

import android.app.Application
import com.nak5.exemplar.di.AppGraph
import dev.zacsweers.metro.createGraph

class MyApplication : Application() {
    val appGraph = createGraph<AppGraph>()
}