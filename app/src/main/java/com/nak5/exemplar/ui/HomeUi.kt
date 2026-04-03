package com.nak5.exemplar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nak5.exemplar.screens.HomeScreen
import com.nak5.exemplar.ui.theme.MyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUi(
    state: HomeScreen.State,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text("Names") })
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(state.items) {
                Text(
                    modifier = Modifier
                        .clickable { state.onEvent(HomeScreen.Event.OnItemClick(it)) }
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    text = it,
                )
            }
        }
    }
}

@Preview
@Composable
internal fun HomeUiPreview() {
    MyTheme {
        HomeUi(
            state = HomeScreen.State(
                items = listOf("Bob", "Alice", "Sam"),
                onEvent = {},
            ),
            modifier = Modifier.fillMaxSize().background(Color.White),
        )
    }
}
