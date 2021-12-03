package net.givtapp.codeshare.infrastructure.settings.serializables

import kotlinx.serialization.Serializable
import net.givtapp.codeshare.registration.RegFlowStep
import net.givtapp.codeshare.registration.RegFlowType

// Define setting here, inherit from CustomSetting!
@Serializable
data class RegistrationSetting(val flowType: RegFlowType, val stepsCompleted: List<RegFlowStep>, val setting_name: SettingName = SettingName.RegFlow): CustomSetting(setting_name)