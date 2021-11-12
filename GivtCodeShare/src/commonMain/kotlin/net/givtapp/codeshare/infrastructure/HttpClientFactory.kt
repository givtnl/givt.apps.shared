package net.givtapp.codeshare.infrastructure

import io.ktor.client.*

expect class HttpClientFactory() {
    fun createHttpClient(baseUrl: String): HttpClient
}