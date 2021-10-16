package net.givtapp.codeshare.creditcards

import net.givtapp.codeshare.extensions.countOfDigits

class CreditCardValidator {
    var creditCard: CreditCard = CreditCard()

    fun cardNumberIsValid(): Boolean {
        if (creditCard.number.isNullOrEmpty())
            return false
        if (!validationRule.isCardNumberValidWithRule(creditCard.number!!))
            return false
        return isValidLuhn(creditCard.number!!)
    }
    fun expiryDateIsValid(): Boolean {
        if (creditCard.expiryDate.month == null || creditCard.expiryDate.year == null)
            return false
        if (creditCard.expiryDate.month!! > 12 ||
            creditCard.expiryDate.year!!  < localDate.year)
            return false
        return creditCard.expiryDate.lastDayOfYearMonthDate >= localDate
    }

    fun securityCodeIsValid(): Boolean {
        if (creditCard.securityCode.isNullOrEmpty())
            return false
        return validationRule.isSecurityCodeValidWithRule(creditCard.securityCode?.toInt()!!.countOfDigits)
    }

    val isValidCreditCard: Boolean
        get () = cardNumberIsValid() && expiryDateIsValid() && securityCodeIsValid()

    // Luhn validation
    private fun isValidLuhn(number: String): Boolean {
        var checksum: Int = 0

        for (i in number.length - 1 downTo 0 step 2) {
            checksum += number[i] - '0'
        }
        for (i in number.length - 2 downTo 0 step 2) {
            val n: Int = (number[i] - '0') * 2
            checksum += if (n > 9) n - 9 else n
        }
        return checksum%10 == 0
    }

    private val validationRule: CreditCardValidationRule
        get () = creditCardValidationRules.getValidationRuleForCompany(creditCard.company)
}
