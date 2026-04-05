package com.nak5.exemplar.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nak5.exemplar.screens.HomeScreen
import com.nak5.exemplar.ui.theme.MyTheme
import com.nak5.exemplar.uistate.CircuitStateHolder
import com.nak5.exemplar.uistate.HomeUiEvent
import com.nak5.exemplar.uistate.HomeUiState
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent

@CircuitInject(HomeScreen::class, SingletonComponent::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUi(
    stateHolder: CircuitStateHolder<HomeUiState>,
    modifier: Modifier = Modifier,
) {
    MyTheme {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(title = { Text("Repos") })
            }
        ) { padding ->
            when (val state = stateHolder.state) {
                is HomeUiState.Loading -> HomeUiLoading(modifier = Modifier.padding(padding))
                is HomeUiState.Loaded ->
                    HomeUiLoaded(
                        modifier = Modifier.padding(padding),
                        state = state,
                        onEvent = stateHolder.onEvent
                    )
            }
        }
    }
}

@Composable
fun HomeUiLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun HomeUiLoaded(
    state: HomeUiState.Loaded,
    onEvent: (HomeUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(state.items) {
            Text(
                modifier = Modifier
                    .clickable { onEvent(HomeUiEvent.OnItemClick(it)) }
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                text = it,
            )
        }
    }
}

@Preview(name = "light")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "large", fontScale = 2f)
@Composable
internal fun HomeUiPreview_Loaded() {
    MyTheme {
        HomeUi(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            stateHolder = CircuitStateHolder(
                state = HomeUiState.Loaded(
                    items = listOf("Repo1", "Repo2", "Repo3"),
                ),
                onEvent = {},
            )
        )
    }
}
