package com.nak5.exemplar.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nak5.exemplar.screens.DetailScreen
import com.nak5.exemplar.ui.theme.MyTheme
import com.nak5.exemplar.uistate.DetailUiState
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent

@CircuitInject(DetailScreen::class, SingletonComponent::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUi(
    state: DetailUiState,
    modifier: Modifier = Modifier,
) {
    MyTheme {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(title = { Text("Repo") })
            }
        ) { padding ->
            Box(
                modifier = modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(state.name)
            }
        }
    }
}
