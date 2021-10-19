package net.givtapp.codeshare.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import net.givtapp.codeshare.GivtDI
import net.givtapp.codeshare.apiclients.givt.infra.InfraClient
import kotlin.test.Test
import kotlin.test.assertTrue

class UtilitiesServiceTests {
    @ExperimentalCoroutinesApi
    @Test
    fun testStatus() = runBlocking {
        val api: InfraClient = GivtDI.infraClient()
        assertTrue { api.getStatus() }
    }
}