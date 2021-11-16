package net.givtapp.codeshare.infrastructure.validators

import net.givtapp.codeshare.infrastructure.validators.infrastructure.*

class PhoneNumberValidator: Validator<String?, String> {
    companion object {
        private val PHONE_NUMBER_REGEX = Regex("[+]?[0-9.-]+")
    }
    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(PhoneNumberValidationError.InputIsNull)
        if (!input.matches(PHONE_NUMBER_REGEX)) return Invalid(PhoneNumberValidationError.InvalidFormat)
        return Valid(input)
    }
}
sealed class PhoneNumberValidationError(override val details: String? = null) : IValidationError {
    object InputIsNull : PhoneNumberValidationError("Input is not a valid Phone Number because it is null.")
    object InvalidFormat : PhoneNumberValidationError("Input is not in a valid Phone Number Format.")
}