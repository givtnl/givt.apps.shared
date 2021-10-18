package net.givtapp.codeshare.apiclients.givt.account

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import net.givtapp.codeshare.apiclients.givt.account.models.CreateAccountCreditCardBody

class AccountClient(private val httpClient: HttpClient) {
    suspend fun createAccount(requestBody: CreateAccountCreditCardBody): HttpResponse {
        return httpClient.post("/api/usersextension") { body = requestBody }
    }
}