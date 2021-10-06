package net.givtapp.shared

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * A CreditCard Validator.
 * This class contains multiple functions for validation
 * @constructor Creates a new validator
 * Use either the method validate or isValidCreditCardNumber and isValidExpiryDate
 * validate will return either an error or the formatted creditcard number
 */
class CreditCardValidator {
    // Just a simple private method
    private fun String.removeWhitespaces() = replace(" ", "")

    fun validate(input: Array<String>): CreditCardValidationResult {
        if (!isValidCreditCardNumber(input.first()))
            return CreditCardValidationResult(false, "Invalid creditcard number", null)
        if (!isValidExpiryDate(input.last()))
            return CreditCardValidationResult(false, "Invalid expiry date", null)
        return CreditCardValidationResult(true, null, getFormatted(input.first()))
    }

    fun isValidCreditCardNumber(inputString: String): Boolean {
        return inputString != "" &&
               inputString.removeWhitespaces().length == 16 ||
               inputString.removeWhitespaces().length == 15
    }

    fun isValidExpiryDate(inputString: String): Boolean {
        val datetimeInSystemZone: LocalDateTime = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val currentMonth: Int = datetimeInSystemZone.monthNumber
        val currentYear: Int = datetimeInSystemZone.year.toString().chunked(2).last().toInt()

        return inputString != "" &&
            inputString.removeWhitespaces().length == 4 &&
            ((inputString.chunked(2).first().toInt() >= currentMonth &&
            inputString.chunked(2).last().toInt() >= currentYear) ||
            inputString.chunked(2).last().toInt() > currentYear)
    }

    fun getFormatted(inputString: String): String {
        return inputString.chunked(4).joinToString(" ")
    }
}

class CreditCardValidationResult(
    override val isValid: Boolean,
    override val error: String?,
    override val formatted: String?
) : ICreditCardValidationResult

interface ICreditCardValidationResult {
    val isValid: Boolean
    val error: String?
    val formatted: String?
}