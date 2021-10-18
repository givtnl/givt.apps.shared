package net.givtapp.codeshare.apiclients.givt.userextension.models

data class UpdateUserExtensionBody(
    val GUID: String,
    val FirstName: String,
    val LastName: String,
    val PhoneNumber: String,
    val Password: String,
)
