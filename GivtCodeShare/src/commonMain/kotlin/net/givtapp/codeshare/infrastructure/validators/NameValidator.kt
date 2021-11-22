package net.givtapp.codeshare.infrastructure.validators

import net.givtapp.codeshare.infrastructure.validators.infrastructure.*

internal class NameValidator : Validator<String?, String> {
    companion object {
        private val NAME_LENGTH_RANGE = IntRange(2, 32)
    }
    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(PasswordValidationError.InputIsNull)
        if (!NAME_LENGTH_RANGE.contains(input.length)) return Invalid(PasswordValidationError.InvalidLength)
        return Valid(input)
    }
}

internal sealed class NameValidationError(override val details: String?) : IValidationError {
    object InputIsNull : PasswordValidationError("Input cannot be null.")
    object InvalidLength : PasswordValidationError("Input length is invalid")
}