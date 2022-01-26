package net.givtapp.codeshare.infrastructure.validators

import net.givtapp.codeshare.infrastructure.validators.infrastructure.*

internal class PostalCodeValidator : Validator<String?, String> {
    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(PostalCodeValidationError.InputIsNull)

        if (!input.matches(postalCodeRegex)) return Invalid(PostalCodeValidationError.InvalidFormat)

        return Valid(input)
    }
    companion object {
        private val postalCodeRegex = Regex("(^\\d{5}\$)|(^\\d{5}-\\d{4}\$)")
    }
}

sealed class PostalCodeValidationError(override val details: String? = null) : IValidationError {
    object InputIsNull : PostalCodeValidationError("Input is not a valid Postal Code because it is null.")
    object InvalidFormat : PostalCodeValidationError("Input is not in a valid Postal Code Format.")
}