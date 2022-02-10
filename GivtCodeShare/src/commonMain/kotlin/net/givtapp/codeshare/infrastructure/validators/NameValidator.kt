package net.givtapp.codeshare.infrastructure.validators

import net.givtapp.codeshare.infrastructure.validators.infrastructure.*

internal class NameValidator : Validator<String?, String> {
    companion object {
        private val NAME_LENGTH_RANGE = IntRange(2, 32)
    }
    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(NameValidationError.InputIsNull)
        if (!NAME_LENGTH_RANGE.contains(input.length)) return Invalid(NameValidationError.InvalidLength)
        return Valid(input)
    }
}

internal sealed class NameValidationError(override val details: String?) : IValidationError {
    object InputIsNull : NameValidationError("Input cannot be null.")
    object InvalidLength : NameValidationError("Input length is invalid")
}