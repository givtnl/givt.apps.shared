package net.givtapp.codeshare.api

import kotlinx.coroutines.runBlocking
import net.givtapp.codeshare.api.User.Register.RegisterUserCommandBody
import net.givtapp.codeshare.infrastructure.exceptions.ClientRequestExceptionResponse
import net.givtapp.codeshare.infrastructure.exceptions.ServerResponseModel
import kotlin.test.Test

class GivtApiTests {

    @Test
    fun ensureValidationErrorsAreCorrectlyHandled() {
        runBlocking {
            kotlin.runCatching {
                GivtApi().registerUser(
                    RegisterUserCommandBody(
                    "B1A52E6B-A2C4-4A49-8684-836CC35B33B9",
                    "givttest+vi7oevwyvcpl0wm@gmail.com",
                    "001244546636",
                    "Test12",
                    "en",
                    1,
                    "US"
                )
                )
            }.onSuccess {
                print("ok")
            }.onFailure {
                when(it) {
                    is ClientRequestExceptionResponse -> print(it.getServerResponse<ServerResponseModel>())
                }
            }
        }
    }
}