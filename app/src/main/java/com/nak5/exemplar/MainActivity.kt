package com.nak5.exemplar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nak5.exemplar.screens.HomeScreen
import com.nak5.exemplar.ui.theme.MyTheme
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.navstack.rememberSaveableNavStack
import com.slack.circuit.foundation.rememberCircuitNavigator

class MainActivity : ComponentActivity() {
    private val circuit by lazy {
        (application as MyApplication).appGraph.circuit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircuitCompositionLocals(circuit) {
                val navStack = rememberSaveableNavStack(root = HomeScreen)
                val navigator = rememberCircuitNavigator(navStack)
                MyTheme {
                    NavigableCircuitContent(navigator, navStack)
                }
            }
        }
    }
}