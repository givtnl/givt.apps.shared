package net.givtapp.codeshare

import io.ktor.client.*
import net.givtapp.codeshare.apiclients.givt.userextension.UserExtensionClient
import net.givtapp.codeshare.apiclients.givt.infra.InfraClient
import net.givtapp.codeshare.apiclients.HttpClientFactory
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object GivtSDK {
    private val sharedModule = DI.Module("App/Shared") {
        bind<HttpClientFactory>() with singleton { HttpClientFactory() }
        bind<HttpClient>() with singleton { instance<HttpClientFactory>().createHttpClient(instance("API_BASE_URL")) }
        constant("API_BASE_URL") with "https://givt-debug-api.azurewebsites.net"
    }

    private val utilitiesModule = DI.Module("App/Utility") {
        bind<InfraClient>() with singleton { InfraClient(instance()) }
    }

    private val userModule = DI.Module("App/User") {
        bind<UserExtensionClient>() with singleton { instance() }
    }

    val diContainer = DI {
        importAll(
            sharedModule,
            utilitiesModule,
            userModule
        )
    }

    fun infraClient() = diContainer.direct.instance<InfraClient>()
}
