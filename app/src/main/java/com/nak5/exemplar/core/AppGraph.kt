package com.nak5.exemplar.core

import com.slack.circuit.foundation.Circuit
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(AppScope::class)
interface AppGraph {
    val circuit: Circuit
}