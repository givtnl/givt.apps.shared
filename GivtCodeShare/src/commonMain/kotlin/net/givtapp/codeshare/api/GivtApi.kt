package net.givtapp.codeshare.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import net.givtapp.codeshare.api.Accounts.AccountDetailModel
import net.givtapp.codeshare.api.Accounts.CreditCard.Register.RegisterCreditCardCommandResponse
import net.givtapp.codeshare.api.Accounts.CreditCard.Register.RegisterCreditCardCommandBody
import net.givtapp.codeshare.api.User.Register.RegisterUserCommandBody
import net.givtapp.codeshare.api.User.UserDetailModel
import net.givtapp.codeshare.infrastructure.HttpClientFactory

class GivtApi {
    internal val httpClient: HttpClient = HttpClientFactory().createHttpClient("https://apidebug.givtapp.net")

    /**
     * Contains implementations for:
     *
     * Users
     * Users/Register/POST
     * Users/Accounts
     * Users/Accounts/POST
     * Users/Accounts/GET
     */

    inner class User {
        @Throws(Exception::class)
        suspend fun registerUser(registerUserCommandBody: RegisterUserCommandBody): UserDetailModel {
            val response: HttpResponse = httpClient.post {
                url { pathComponents("api", "v2", "users", "register") }
                body = registerUserCommandBody
            }
            return response.receive()
        }
        // Accounts
        inner class Accounts {
            @Throws(Exception::class)
            suspend fun registerCreditCard(userId: String, bearerToken: String, registerCreditCardCommandBody: RegisterCreditCardCommandBody): RegisterCreditCardCommandResponse {
                val response: HttpResponse = httpClient.post {
                    url { pathComponents( "api", "v2", "users", userId, "accounts")}
                    body = registerCreditCardCommandBody
                    headers {
                        header("Authorization", bearerToken)
                    }
                }
                return response.receive()
            }

            @Throws(Exception::class)
            suspend fun getAccounts(userId: String, bearerToken: String): List<AccountDetailModel> {
                val response: HttpResponse = httpClient.get {
                    url { pathComponents("api", "v2", "users", userId, "accounts") }
                    headers {
                        header("Authorization", bearerToken)
                    }
                }
                return response.receive()
            }
        }
    }
}

