package net.givtapp.codeshare.api.Accounts.CreditCard.Register

import kotlinx.serialization.Serializable
import net.givtapp.codeshare.api.Accounts.CreditCard.CreditCardDetails

@Serializable
class RegisterCreditCardCommandResponse(
    val Id: Int,
    val CreditCardDetails: CreditCardDetails
)