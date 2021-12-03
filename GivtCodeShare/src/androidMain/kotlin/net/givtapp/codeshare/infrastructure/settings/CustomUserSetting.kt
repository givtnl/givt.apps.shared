package net.givtapp.codeshare.infrastructure.settings

import android.content.Context
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.givtapp.codeshare.infrastructure.settings.serializables.CustomSetting
import net.givtapp.codeshare.infrastructure.settings.serializables.GivtSharedPreferences
import net.givtapp.codeshare.infrastructure.settings.serializables.RegistrationSetting
import net.givtapp.codeshare.infrastructure.settings.serializables.SettingName

actual class CustomUserSetting(val anyContext: Context) {

    var storedPreferencesString: String
        get () {
            return anyContext.getSharedPreferences("net.givtapp.droid2",
                Context.MODE_PRIVATE
            ).getString("STORED_PREFERENCES", "{}").toString()
        }
        set(value) {
            anyContext.getSharedPreferences("net.givtapp.droid2",
                Context.MODE_PRIVATE
            ).edit().putString("STORED_PREFERENCES", value).apply()
        }

    var storedPreferences: GivtSharedPreferences = GivtSharedPreferencesHelper(storedPreferencesString).localPreferences

    actual fun saveSetting(setting: CustomSetting) {
        when(setting.name) {
            SettingName.RegFlow -> storedPreferences.registrationFlow = (setting as RegistrationSetting)
            else -> {
                return
            }
        }
        storedPreferencesString = Json.encodeToString(storedPreferences)
    }

    actual fun getSetting(setting: CustomSetting): CustomSetting? {
        return when(setting.name) {
            SettingName.RegFlow -> storedPreferences.registrationFlow
            else -> {
                return null
            }
        }
    }
}