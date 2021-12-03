package net.givtapp.codeshare.infrastructure.settings

import net.givtapp.codeshare.infrastructure.settings.serializables.CustomSetting

expect class CustomUserSetting {
    fun saveSetting(setting: CustomSetting)
    fun getSetting(setting: CustomSetting): CustomSetting?
}