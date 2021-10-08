package net.givtapp.codeshare.creditcards

import net.givtapp.codeshare.extensions.toCardStyle

class CreditCard(userInput: String) {
    val number = userInput.filter { it.isDigit() }
    val company = getCreditCardCompany()
    val formatted = number.toCardStyle
    private val firstDigit: Int = if (number.isNotEmpty()) number.first().toString().toInt() else 0
    private fun getCreditCardCompany(): CreditCardCompany {
        return when (firstDigit) {
            2, 4 -> CreditCardCompany.Visa
            5 -> CreditCardCompany.Mastercard
            3 -> CreditCardCompany.AmericanExpress
            6 -> CreditCardCompany.Discovery
            else -> CreditCardCompany.Undefined
        }
    }
}
