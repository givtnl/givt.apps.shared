package net.givtapp.codeshare.apiclients

import io.ktor.client.*

internal expect class HttpClientProvider() {
    fun getHttpClient(baseUrl: String): HttpClient
    fun bearerToken(): String?
}