package net.givtapp.codeshare.infrastructure.settings.serializables

import kotlinx.serialization.Serializable

@Serializable
enum class SettingName (val stringName: String) {
    RegFlow("REG_FLOW_SETTING")
}