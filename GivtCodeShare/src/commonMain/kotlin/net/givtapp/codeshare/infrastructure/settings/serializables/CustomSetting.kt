package net.givtapp.codeshare.infrastructure.settings.serializables

import kotlinx.serialization.Serializable

@Serializable
open class CustomSetting(val name: SettingName)