package com.nak5.exemplar.network

import app.cash.turbine.awaitItem
import com.nak5.exemplar.network.data.Repo
import kotlinx.coroutines.channels.Channel

class FakeNetworkService : NetworkService {

    val repos = Channel<List<Repo>>()
    override suspend fun listRepos(user: String): List<Repo> {
        return repos.awaitItem()
    }
}