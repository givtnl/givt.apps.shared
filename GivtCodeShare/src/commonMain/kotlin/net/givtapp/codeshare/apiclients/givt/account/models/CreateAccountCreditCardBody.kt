package net.givtapp.codeshare.apiclients.givt.account.models

data class CreateAccountCreditCardBody(
    val CardNumber: String,
    val ExpirationMonth: Int,
    val ExpirationYear: Int,
)
