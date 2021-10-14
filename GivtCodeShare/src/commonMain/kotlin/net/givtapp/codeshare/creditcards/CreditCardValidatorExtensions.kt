package net.givtapp.codeshare.creditcards

import kotlinx.datetime.*
import net.givtapp.codeshare.extensions.getLastDayOfYearMonth

// Extension property that holds CreditCardValidationRules
internal val CreditCardValidator.creditCardValidationRules: Collection<CreditCardValidationRule>
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

// Extension property getter to get current LocalDate
internal val CreditCardValidator.localDate: LocalDate
    get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

// Static extension method on specific collection of where T: CreditCardValidationRule
internal fun Collection<CreditCardValidationRule>.getValidationRuleForCompany(creditCardCompany: CreditCardCompany): CreditCardValidationRule {
    return when (creditCardCompany) {
        CreditCardCompany.Visa -> this.first { it.company == CreditCardCompany.Visa }
        CreditCardCompany.Mastercard -> this.first { it.company == CreditCardCompany.Mastercard }
        CreditCardCompany.AmericanExpress -> this.first { it.company == CreditCardCompany.AmericanExpress }
        CreditCardCompany.Discover -> this.first { it.company == CreditCardCompany.Discover }
        CreditCardCompany.Undefined -> this.first { it.company == CreditCardCompany.Undefined }
    }
}