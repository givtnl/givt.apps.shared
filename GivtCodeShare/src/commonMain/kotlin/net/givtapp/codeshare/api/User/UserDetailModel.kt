package net.givtapp.codeshare.api.User

import kotlinx.serialization.Serializable

@Serializable
class UserDetailModel(
    val Id: String,
    val Email: String,
    val Country: String,
    val Language: String,
    val PhoneNumber: String)