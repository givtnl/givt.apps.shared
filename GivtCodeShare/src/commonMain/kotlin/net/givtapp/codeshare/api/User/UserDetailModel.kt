package net.givtapp.codeshare.api.User

import kotlinx.serialization.Serializable

@Serializable
class UserDetailModel(
    val Id: String,
    val Email: String,
    val Country: String,
    val Language: String,
    val PhoneNumber: String,
    val Iban: String? = null,
    val SortCode: String? = null,
    val AccountNumber: String? = null,
    val FirstName: String? = null,
    val LastName: String? = null,
    val IsDisabled: Boolean? = null,
    val City: String? = null,
    val Address: String? = null,
    val PostalCode: String? = null,
    val DetailLineOne: String? = null,
    val DetailLineTwo: String? = null,
    val DetailLineThree: String? = null
)