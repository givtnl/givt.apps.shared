package net.givtapp.codeshare.creditcards

internal class CreditCardValidationRule {
    var company: CreditCardCompany? = null
    private var startsWith: Array<Int>? = null
    private var maxLength: Array<Int> = arrayOf()
    fun creditCardCompany(creditCardCompany: CreditCardCompany) =
        apply { this.company = creditCardCompany }

    fun startsWith(vararg startsWith: Int) =
        apply { this.startsWith = startsWith.toTypedArray() }

    fun maxLength(vararg length: Int) =
        apply { this.maxLength = length.toTypedArray()}

    fun validateWithRule(inputLength: Int): Boolean {
        return isLengthValid(inputLength)
    }

    // Returns true when no maxLength given or when length matches either value in the array
    private fun isLengthValid(length: Int): Boolean {
        return if (company == CreditCardCompany.Undefined)
            IntRange(maxLength.first(), maxLength.last()).contains(length)
        else
            maxLength.first() == length
    }
}