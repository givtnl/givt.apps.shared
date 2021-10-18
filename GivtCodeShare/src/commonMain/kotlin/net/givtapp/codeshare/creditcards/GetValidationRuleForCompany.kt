package net.givtapp.codeshare.creditcards

internal fun Collection<CreditCardValidationRule>.getValidationRuleForCompany(creditCardCompany: CreditCardCompany): CreditCardValidationRule {
    return when (creditCardCompany) {
        CreditCardCompany.Visa -> this.first { it.company == CreditCardCompany.Visa }
        CreditCardCompany.Mastercard -> this.first { it.company == CreditCardCompany.Mastercard }
        CreditCardCompany.AmericanExpress -> this.first { it.company == CreditCardCompany.AmericanExpress }
        CreditCardCompany.Discover -> this.first { it.company == CreditCardCompany.Discover }
        CreditCardCompany.Undefined -> this.first { it.company == CreditCardCompany.Undefined }
    }
}