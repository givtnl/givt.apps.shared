package net.givtapp.codeshare.api.Accounts

import kotlinx.serialization.Serializable
import net.givtapp.codeshare.api.Accounts.CreditCard.CreditCardDetails

@Serializable
class AccountDetailModel(
    val Id: Int,
    val Primary: Boolean,
    val Active: Boolean,
    val Verified: Boolean,
    val CreditCardDetails: CreditCardDetails
)