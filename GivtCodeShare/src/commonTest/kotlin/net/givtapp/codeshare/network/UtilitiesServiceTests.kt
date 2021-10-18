package net.givtapp.codeshare.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import net.givtapp.codeshare.GivtSDK
import net.givtapp.codeshare.apiclients.givt.infra.InfraClient
import org.kodein.di.instance
import kotlin.test.Test
import kotlin.test.assertTrue

class UtilitiesServiceTests {
    private val di = GivtSDK.diContainer

    @ExperimentalCoroutinesApi
    @Test
    fun testStatus() = runBlocking {
        val api: InfraClient by di.instance()
        assertTrue { api.getStatus() }
    }
}