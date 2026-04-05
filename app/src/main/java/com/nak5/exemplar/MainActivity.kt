package com.nak5.exemplar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.nak5.exemplar.screens.HomeScreen
import com.nak5.exemplar.ui.theme.MyTheme
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.navstack.rememberSaveableNavStack
import com.slack.circuit.foundation.rememberCircuitNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var circuit: Circuit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircuitCompositionLocals(circuit) {
                val navStack = rememberSaveableNavStack(root = HomeScreen)
                val navigator = rememberCircuitNavigator(navStack)
                MyTheme {
                    Box(modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                    ) {
                        NavigableCircuitContent(navigator, navStack)
                    }
                }
            }
        }
    }
}