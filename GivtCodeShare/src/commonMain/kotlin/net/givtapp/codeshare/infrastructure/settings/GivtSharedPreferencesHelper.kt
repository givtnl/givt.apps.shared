package net.givtapp.codeshare.infrastructure.settings

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.givtapp.codeshare.infrastructure.settings.serializables.GivtSharedPreferences
import net.givtapp.codeshare.infrastructure.settings.serializables.RegistrationSetting

class GivtSharedPreferencesHelper(preferences: String) {
    var localPreferences: GivtSharedPreferences = Json.decodeFromString(preferences)
    // Add nullable setting here and initialize with null
    var registrationFlow: RegistrationSetting? = null

    init {
        // Read from localpreferences and fill in property here
        registrationFlow = localPreferences.registrationFlow
    }
}