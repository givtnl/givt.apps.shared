package net.givtapp.codeshare.creditcards

import net.givtapp.codeshare.extensions.isNull
import net.givtapp.codeshare.extensions.toCardStyle

class CreditCard {
    private var creditCardNumber: String? = null
    private var creditCardExpiryDate: CreditCardExpiryDateModel = CreditCardExpiryDateModel()
    private var creditCardSecurityCode: Int? = null

    val company: CreditCardCompany
        get() {
            return getCreditCardCompany()
        }

    var number: String?
        get () = creditCardNumber?.filter { it.isDigit() }
        set(value) {
            creditCardNumber = value
        }

    val expiryDate: CreditCardExpiryDateModel
        get () = creditCardExpiryDate

    var securityCode: String?
        get () {
            if (!creditCardSecurityCode.isNull)
                return creditCardSecurityCode.toString()
            return null
        }
        set (value) {
            if (!value.isNullOrEmpty())
                creditCardSecurityCode = value.toInt()
        }

    val formatted: String?
        get() = number?.toCardStyle


    private val firstDigit: Int
        get () = if (!number.isNullOrEmpty()) number!!.first().toString().toInt() else 0

    private fun getCreditCardCompany(): CreditCardCompany {
        return when (firstDigit) {
            4 -> CreditCardCompany.Visa
            5, 2 -> CreditCardCompany.Mastercard
            3 -> CreditCardCompany.AmericanExpress
            6 -> CreditCardCompany.Discover
            else -> CreditCardCompany.Undefined
        }
    }
}
