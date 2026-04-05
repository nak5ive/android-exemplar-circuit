package com.nak5.exemplar.presenters

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.nak5.exemplar.network.FakeNetworkService
import com.nak5.exemplar.network.data.Repo
import com.nak5.exemplar.screens.DetailScreen
import com.nak5.exemplar.screens.HomeScreen
import com.nak5.exemplar.uistate.HomeUiEvent
import com.nak5.exemplar.uistate.HomeUiState
import com.slack.circuit.test.FakeNavigator
import com.slack.circuit.test.test
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomePresenterTest {

    val navigator = FakeNavigator(DEFAULT_SCREEN)
    val service = FakeNetworkService()

    private fun makePresenter() =
        HomePresenter(
            screen = DEFAULT_SCREEN,
            navigator = navigator,
            service = service,
        )

    @Test
    fun `default model`() = runTest {
        makePresenter().test {
            assertThat(awaitItem().state).isEqualTo(HomeUiState.Loading)

            service.repos.send(REPOS)
            assertThat(awaitItem().state).isInstanceOf<HomeUiState.Loaded>().given {
                assertThat(it.items).isEqualTo(listOf("Repo1", "Repo2", "Repo3"))
            }
        }
    }

    @Test
    fun `navigates to item`() = runTest {
        makePresenter().test {
            awaitItem()

            service.repos.send(REPOS)
            assertThat(awaitItem()).given {
                assertThat(it.state).isInstanceOf<HomeUiState.Loaded>()

                // do click
                it.onEvent(HomeUiEvent.OnItemClick("Repo2"))
            }

            assertThat(navigator.awaitNextScreen()).isEqualTo(DetailScreen("Repo2"))
        }
    }

    companion object {
        val DEFAULT_SCREEN = HomeScreen

        val REPOS = listOf(
            Repo("Repo1"),
            Repo("Repo2"),
            Repo("Repo3")
        )
    }
}