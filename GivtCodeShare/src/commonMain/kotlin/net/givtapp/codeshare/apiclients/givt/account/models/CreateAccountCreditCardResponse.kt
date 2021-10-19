package net.givtapp.codeshare.apiclients.givt.account.models

data class CreateAccountCreditCardResponse(
    val Id: Int,
    val OrganisationId: String,
    val UserId: String,
    val AccountName: String,
    val Iban: String,
    val AccountNumber: String,
    val SortCode: String,
    val Verified: Boolean,
    val PaymentType: Int,
    val ActiveMandate: String,
    val CreditCardDetails: CreditCardDetail,
)
