package net.givtapp.codeshare.api.User.Register

import kotlinx.serialization.Serializable

@Serializable
open class RegisterUserCommandBody(
    var userId: String,
    var email: String,
    var phoneNumber: String,
    var password: String,
    var appLanguage: String,
    var deviceOS: Int,
    var country: String,
    var firstName: String,
    var lastName: String)