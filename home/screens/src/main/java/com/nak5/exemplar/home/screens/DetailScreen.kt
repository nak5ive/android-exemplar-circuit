package com.nak5.exemplar.home.screens

import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailScreen(
    val name: String,
) : Screen