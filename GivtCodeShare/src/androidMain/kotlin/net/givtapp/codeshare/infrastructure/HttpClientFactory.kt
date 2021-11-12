package net.givtapp.codeshare.infrastructure

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.cio.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.givtapp.codeshare.api.GivtApiResponseModel
import net.givtapp.codeshare.infrastructure.exceptions.ClientRequestExceptionResponse
import kotlin.Exception

actual class HttpClientFactory {
    actual fun createHttpClient(baseUrl: String): HttpClient {
        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            HttpResponseValidator {
                handleResponseException {
                    val clientException = it as? ClientRequestException ?: return@handleResponseException
                    val exceptionResponse = it.response
                    when(exceptionResponse.status.value) {
                        in 400..499 -> throw ClientRequestExceptionResponse(exceptionResponse)
                    }
                }
            }
            defaultRequest {
                url.takeFrom(
                    URLBuilder().takeFrom(baseUrl).apply {
                        encodedPath += url.encodedPath
                    })
                contentType(ContentType.Application.Json)
            }
            engine {
                // this: AndroidEngineConfig
            }
        }
    }
}

