package net.givtapp.codeshare.apiclients

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import net.givtapp.codeshare.GivtDI

internal actual class HttpClientProvider {
    actual fun bearerToken(): String? {
        TODO();
    }
    actual fun getHttpClient(baseUrl: String): HttpClient {
        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                })
            }
            defaultRequest {
                url.takeFrom(
                    URLBuilder().takeFrom(baseUrl).apply {
                        encodedPath += url.encodedPath
                    })
                header(HttpHeaders.ContentType, "application/json")
            }
            engine {
                // this: AndroidEngineConfig
            }
        }
    }
}