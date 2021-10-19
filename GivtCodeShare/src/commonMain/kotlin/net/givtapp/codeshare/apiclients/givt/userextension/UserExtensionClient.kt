package net.givtapp.codeshare.apiclients.givt.userextension

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import net.givtapp.codeshare.apiclients.givt.userextension.models.UpdateUserExtensionBody

class UserExtensionClient(private val httpClient: HttpClient) {
    suspend fun Update(requestBody: UpdateUserExtensionBody): HttpResponse {
        return httpClient.put("/api/usersextension") { body = requestBody }
    }
}