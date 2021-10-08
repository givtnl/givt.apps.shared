package net.givtapp.codeshare.creditcards

import kotlinx.datetime.LocalDate
import net.givtapp.codeshare.extensions.containsOnlyDigitsOrWhitespace
import net.givtapp.codeshare.extensions.daysInMonth

class CreditCardValidator {
    fun creditCardIsValid(userInput: String): Boolean {
        return if (userInput.containsOnlyDigitsOrWhitespace())
            isValidCreditCard(CreditCard(userInput))
        else false
    }

    fun expiryDateIsValid(month: Int, year: Int): Boolean {
        return if (month > 12 || year < localDate.year)
            false
        else isInFuture(month, year)
    }

    private fun isValidCreditCard(creditCard: CreditCard): Boolean {
        val creditCardValidationRule: CreditCardValidationRule =
            creditCardValidationRules.getValidationRuleForCompany(creditCard.company)
        return creditCardValidationRule.validateWithRule(creditCard.number.length) && isValidLuhn(creditCard.number)
    }

    private fun isInFuture(month: Int, year: Int): Boolean {
        val daysInMonth = LocalDate.daysInMonth(month, year)
        return LocalDate(year, month, daysInMonth) >= localDate
    }
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
}
