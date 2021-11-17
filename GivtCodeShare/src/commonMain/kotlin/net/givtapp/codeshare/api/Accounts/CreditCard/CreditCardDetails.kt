package net.givtapp.codeshare.api.Accounts.CreditCard

import kotlinx.serialization.Serializable

@Serializable
class CreditCardDetails(
    val CardNumber: String,
    val CardType: String
)