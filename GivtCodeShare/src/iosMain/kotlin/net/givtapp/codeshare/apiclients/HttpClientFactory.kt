package net.givtapp.codeshare.apiclients

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

internal actual class HttpClientFactory {
    actual fun createHttpClient(baseUrl: String): HttpClient {
        return HttpClient(Ios) {
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
                headers {
                    append(HttpHeaders.ContentType, "application/json")
                }
            }
            engine {
                // this: IosClientEngineConfig
                configureRequest {
                    // this: NSMutableURLRequest
                }
            }
        }
    }
}