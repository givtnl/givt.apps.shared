package net.givtapp.codeshare.api.Accounts.CreditCard.Register

import kotlinx.serialization.Serializable

@Serializable
class RegisterCreditCardCommandBody(var creditCardDetails: RegisterCreditCardCommandBodyDetails)

@Serializable
class RegisterCreditCardCommandBodyDetails(var cardNumber: String, var expirationMonth: Int, var expirationYear: Int)