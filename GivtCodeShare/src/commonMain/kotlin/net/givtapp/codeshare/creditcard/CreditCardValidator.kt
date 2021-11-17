package net.givtapp.codeshare.creditcard

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import net.givtapp.codeshare.infrastructure.extensions.countOfDigits
import net.givtapp.codeshare.infrastructure.extensions.getLastDayOfYearMonth
import net.givtapp.codeshare.infrastructure.extensions.isNull

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
        if (creditCard.expiryDate.month.isNull || creditCard.expiryDate.year.isNull)
            return false
        if (creditCard.expiryDate.month!! > 12 || creditCard.expiryDate.year!! < currentDate.year)
            return false
        val dateFromYearMonth = LocalDate.getLastDayOfYearMonth(creditCard.expiryDate)
        return dateFromYearMonth >= currentDate
    }

    fun securityCodeIsValid(): Boolean {
        if (creditCard.securityCode.isNullOrEmpty())
            return false
        return validationRule.isSecurityCodeValidWithRule(creditCard.securityCode?.toInt()!!.countOfDigits)
    }

    val isValidCreditCard: Boolean
        get() = cardNumberIsValid() && expiryDateIsValid() && securityCodeIsValid()

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
        return checksum % 10 == 0
    }

    private val validationRule: CreditCardValidationRule
        get() = creditCardValidationRules.getValidationRuleForCompany(creditCard.company)

    private val currentDate: LocalDate
        get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    private val creditCardValidationRules: Collection<CreditCardValidationRule>
        get() {
            return listOf(
                CreditCardValidationRule()
                    .creditCardCompany(CreditCardCompany.Visa)
                    .startsWith(4)
                    .hasMaxLengthOf(16)
                    .securityCodeLength(3),

                CreditCardValidationRule()
                    .creditCardCompany(CreditCardCompany.Mastercard)
                    .startsWith(2, 5)
                    .hasMaxLengthOf(16)
                    .securityCodeLength(3),

                CreditCardValidationRule()
                    .creditCardCompany(CreditCardCompany.AmericanExpress)
                    .startsWith(3)
                    .hasMaxLengthOf(15)
                    .securityCodeLength(4),

                CreditCardValidationRule()
                    .creditCardCompany(CreditCardCompany.Discover)
                    .startsWith(6)
                    .hasMaxLengthOf(16)
                    .securityCodeLength(3),

                CreditCardValidationRule()
                    .creditCardCompany(CreditCardCompany.Undefined)
                    .startsWith(0, 1, 7, 8, 9)
                    .hasMaxLengthOf(13, 19)
                    .securityCodeLength(3, 4)
            )
        }
}
