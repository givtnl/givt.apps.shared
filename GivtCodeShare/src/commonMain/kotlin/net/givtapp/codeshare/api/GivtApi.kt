package net.givtapp.codeshare.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import net.givtapp.codeshare.api.Accounts.AccountDetailModel
import net.givtapp.codeshare.api.Accounts.CreditCard.Register.RegisterCreditCardCommandBody
import net.givtapp.codeshare.api.User.Register.RegisterUserCommandBody
import net.givtapp.codeshare.api.User.ShareUserData.ShareUserDataCommandBody
import net.givtapp.codeshare.api.User.UserDetailModel
import net.givtapp.codeshare.infrastructure.HttpClientFactory
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object GivtApi {
    lateinit var httpClient: HttpClient
    fun initSingleton(baseUrl: String) {
        httpClient = HttpClientFactory().createHttpClient(baseUrl)
    }

    /**
     * Contains implementations for:
     *
     * Users
     * Users/Register/POST
     * Users/Accounts
     * Users/Accounts/POST
     * Users/Accounts/GET
     */

    class User {
        @Throws(Exception::class)
        suspend fun registerUser(registerUserCommandBody: RegisterUserCommandBody): UserDetailModel {
            val response: HttpResponse = httpClient.post {
                url { pathComponents("api", "v2", "users", "register") }
                body = registerUserCommandBody
            }
            return response.receive()
        }

        suspend fun getShareUserData(userId: String, bearerToken: String): Boolean {
            val response: HttpResponse = httpClient.get {
                url { pathComponents("api", "v2", "users", userId, "sharedata")}
                headers {
                    header("Authorization", "Bearer $bearerToken")
                }
            }
            return response.receive()
        }

        @Throws(Exception::class)
        suspend fun putShareUserData(userId: String, bearerToken: String, putShareUserDataCommandBody: ShareUserDataCommandBody): Boolean {
            val response: HttpResponse = httpClient.put {
                url { pathComponents("api", "v2", "users", userId, "sharedata")}
                body = putShareUserDataCommandBody.shareData
                headers {
                    header("Authorization", "Bearer $bearerToken")
                }
            }
            return response.receive()
        }

        // Accounts
        class Accounts {
            @Throws(Exception::class)
            suspend fun registerCreditCard(userId: String, bearerToken: String, registerCreditCardCommandBody: RegisterCreditCardCommandBody): String {
                val response: HttpResponse = httpClient.post {
                    url { pathComponents( "api", "v2", "users", userId, "mandates")}
                    body = registerCreditCardCommandBody
                    headers {
                        header("Authorization", "Bearer $bearerToken")
                    }
                }
                return response.receive()
            }

            @Throws(Exception::class)
            suspend fun getAccounts(userId: String, bearerToken: String): List<AccountDetailModel> {
                val response: HttpResponse = httpClient.get {
                    url { pathComponents("api", "v2", "users", userId, "accounts") }
                    headers {
                        header("Authorization", "Bearer $bearerToken")
                    }
                }
                return response.receive()
            }
        }
    }
}

