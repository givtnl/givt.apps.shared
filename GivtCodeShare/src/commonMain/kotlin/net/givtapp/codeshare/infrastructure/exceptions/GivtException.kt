package net.givtapp.codeshare.infrastructure.exceptions

import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.givtapp.codeshare.infrastructure.models.GivtHttpStatusCode


class ClientRequestExceptionResponse(val response: HttpResponse) : Exception() {
    val statusCode: HttpStatusCode get () = response.status
    suspend inline fun <reified T>getServerResponse(): T {
        val responseString = getServerResponseString()
        return Json.decodeFromString(responseString)
    }
    suspend fun getServerResponseString(): String {
        return response.content.readUTF8Line().toString()
    }
}

@Serializable
data class ServerResponseModel(
    val AdditionalInformation: HashMap<String, String>? = null,
    val ErrorCode: GivtHttpStatusCode,
    val Message: String
)

class UnhandledException(val statusCode: HttpStatusCode, message: String) : Exception(message)