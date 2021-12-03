package net.givtapp.codeshare.infrastructure.settings

import net.givtapp.codeshare.infrastructure.settings.serializables.CustomSetting
import net.givtapp.codeshare.infrastructure.settings.serializables.GivtSharedPreferences
import net.givtapp.codeshare.infrastructure.settings.serializables.RegistrationSetting
import net.givtapp.codeshare.infrastructure.settings.serializables.SettingName

actual class CustomUserSetting {
    var storedPreferencesString: String = NSUserDefaults.standardUserDefaults.stringForKey("STORED_PREFERENCES")

    var storedPreferences: GivtSharedPreferences = GivtSharedPreferencesHelper(storedPreferencesString).localPreferences

    actual fun saveSetting(setting: CustomSetting, value: String) {
        when(setting.name) {
            SettingName.RegFlow -> storedPreferences.registrationFlow = (setting as RegistrationSetting)
            else -> {
                return
            }
        }
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