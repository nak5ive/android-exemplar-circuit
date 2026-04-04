package com.nak5.exemplar.home.presenters

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.nak5.exemplar.home.screens.DetailScreen
import com.nak5.exemplar.home.screens.HomeScreen
import com.nak5.exemplar.home.uistate.HomeUiEvent
import com.nak5.exemplar.home.uistate.HomeUiState
import com.nak5.exemplar.network.FakeNetworkService
import com.nak5.exemplar.network.data.Repo
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
            assertThat(awaitItem()).isEqualTo(HomeUiState.Loading)

            service.repos.send(REPOS)
            assertThat(awaitItem()).isInstanceOf<HomeUiState.Loaded>().given {
                assertThat(it.items).isEqualTo(listOf("Repo1", "Repo2", "Repo3"))
            }
        }
    }

    @Test
    fun `navigates to item`() = runTest {
        makePresenter().test {
            awaitItem()

            service.repos.send(REPOS)
            assertThat(awaitItem()).isInstanceOf<HomeUiState.Loaded>().given {
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