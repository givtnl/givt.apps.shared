package net.givtapp.codeshare.creditcards

internal class CreditCardValidationRule {
    var company: CreditCardCompany? = null
    private var startsWith: Array<Int>? = null
    private var cardNumberLength: Array<Int> = arrayOf()
    private var securityCodeLength: Array<Int> = arrayOf()
    fun creditCardCompany(creditCardCompany: CreditCardCompany) =
        apply { this.company = creditCardCompany }

    fun startsWith(vararg startsWith: Int) =
        apply { this.startsWith = startsWith.toTypedArray() }

    fun hasMaxLengthOf(vararg length: Int) =
        apply { this.cardNumberLength = length.toTypedArray()}

    fun securityCodeLength(vararg length: Int) =
        apply { this.securityCodeLength = length.toTypedArray() }

    fun isCardNumberValidWithRule(cardNumber: String): Boolean {
        return if (company == CreditCardCompany.Undefined)
            IntRange(cardNumberLength.first(), cardNumberLength.last()).contains(cardNumber.count())
        else
            cardNumberLength.first() == cardNumber.count()
    }
    fun isSecurityCodeValidWithRule(length: Int): Boolean {
        return if (company == CreditCardCompany.Undefined)
            IntRange(securityCodeLength.first(), securityCodeLength.last()).contains(length)
        else
            securityCodeLength.first() == length
    }
}
