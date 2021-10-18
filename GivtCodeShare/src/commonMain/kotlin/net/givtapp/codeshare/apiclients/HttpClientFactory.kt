package net.givtapp.codeshare.apiclients

import io.ktor.client.*

internal expect class HttpClientFactory() {
    fun createHttpClient(baseUrl: String): HttpClient
}