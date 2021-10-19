package net.givtapp.codeshare.apiclients

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import platform.Foundation.NSUserDefaults

internal actual class HttpClientProvider {
    actual fun bearerToken(): String? {
        return NSUserDefaults.standardUserDefaults.stringForKey("bearerToken")
    }
    actual fun getHttpClient(baseUrl: String): HttpClient {
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
                header(HttpHeaders.ContentType, "application/json")
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