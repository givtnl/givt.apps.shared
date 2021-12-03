package net.givtapp.codeshare.infrastructure.settings.serializables

import kotlinx.serialization.Serializable

@Serializable
data class GivtSharedPreferences(
    var registrationFlow: RegistrationSetting? = null
)