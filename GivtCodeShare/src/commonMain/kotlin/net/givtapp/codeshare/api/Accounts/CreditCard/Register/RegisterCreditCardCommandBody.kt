package net.givtapp.codeshare.api.Accounts.CreditCard.Register

import kotlinx.serialization.Serializable

@Serializable
open class RegisterCreditCardCommandBody(val paymentMethodToken: String, val userId: String)