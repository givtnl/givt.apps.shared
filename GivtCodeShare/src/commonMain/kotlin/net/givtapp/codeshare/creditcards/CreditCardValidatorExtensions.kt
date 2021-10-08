package net.givtapp.codeshare.creditcards

import kotlinx.datetime.*

// Extension property that holds CreditCardValidationRules
internal val CreditCardValidator.creditCardValidationRules: Collection<CreditCardValidationRule>
    get() {
        return listOf(
            CreditCardValidationRule()
                .creditCardCompany(CreditCardCompany.Visa)
                .startsWith(4)
                .maxLength(16),
            CreditCardValidationRule()
                .creditCardCompany(CreditCardCompany.Mastercard)
                .startsWith(2, 5)
                .maxLength(16),
            CreditCardValidationRule()
                .creditCardCompany(CreditCardCompany.AmericanExpress)
                .startsWith(3)
                .maxLength(15),
            CreditCardValidationRule()
                .creditCardCompany(CreditCardCompany.Discovery)
                .startsWith(6)
                .maxLength(16),
            CreditCardValidationRule()
                .creditCardCompany(CreditCardCompany.Undefined)
                .maxLength(13, 19)
        )
    }
// Extension property getter to get current LocalDate
internal val CreditCardValidator.localDate: LocalDate
    get () = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

// Static extension method on specific collection of where T: CreditCardValidationRule
internal fun Collection<CreditCardValidationRule>.getValidationRuleForCompany(creditCardCompany: CreditCardCompany): CreditCardValidationRule {
    return when (creditCardCompany) {
        CreditCardCompany.Visa -> this.first { it.company == CreditCardCompany.Visa }
        CreditCardCompany.Mastercard -> this.first {it.company == CreditCardCompany.Mastercard}
        CreditCardCompany.AmericanExpress -> this.first {it.company == CreditCardCompany.AmericanExpress}
        CreditCardCompany.Discovery -> this.first {it.company == CreditCardCompany.Discovery }
        CreditCardCompany.Undefined -> this.first { it.company == CreditCardCompany.Undefined }
    }
}
