package net.givtapp.codeshare.infrastructure

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

actual class HttpClientFactory {
    actual fun createHttpClient(baseUrl: String): HttpClient {
        return HttpClient(Ios) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }
            defaultRequest {
                url.takeFrom(
                    URLBuilder().takeFrom(baseUrl).apply {
                        encodedPath += url.encodedPath
                    })
                contentType(ContentType.Application.Json)
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