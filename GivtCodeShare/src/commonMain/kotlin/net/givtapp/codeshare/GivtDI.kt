package net.givtapp.codeshare

import io.ktor.client.*
import net.givtapp.codeshare.apiclients.givt.userextension.UserExtensionClient
import net.givtapp.codeshare.apiclients.givt.infra.InfraClient
import net.givtapp.codeshare.apiclients.HttpClientProvider
import net.givtapp.codeshare.apiclients.givt.account.AccountClient
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object GivtDI {
    var bearerToken: String? = null

    private val sharedModule = DI.Module("App/Shared") {
        bind<HttpClientProvider>() with singleton { HttpClientProvider() }
        bind<HttpClient>() with singleton { instance<HttpClientProvider>().getHttpClient(instance("API_BASE_URL")) }
        constant("API_BASE_URL") with "https://givt-debug-api.azurewebsites.net"
    }

    private val utilitiesModule = DI.Module("App/Utility") {
        bind<InfraClient>() with singleton { InfraClient(instance()) }
    }

    private val userModule = DI.Module("App/User") {
        bind<UserExtensionClient>() with singleton { instance() }
    }

    private val diContainer = DI {
        importAll(
            sharedModule,
            utilitiesModule,
            userModule
        )
    }

    val infraClient get () = diContainer.direct.instance<InfraClient>()
    val userExtensionClient get () = diContainer.direct.instance<InfraClient>()
    val accountClient get () = diContainer.direct.instance<AccountClient>()
}
