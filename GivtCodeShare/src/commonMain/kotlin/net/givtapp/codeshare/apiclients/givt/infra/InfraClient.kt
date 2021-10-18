package net.givtapp.codeshare.apiclients.givt.infra

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class InfraClient(private val httpClient: HttpClient) {
    suspend fun getStatus(): Boolean {
        return (httpClient.get("/api/v2/status") as HttpResponse).status.isSuccess()
    }
}