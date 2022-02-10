package net.givtapp.codeshare.infrastructure.validators

import net.givtapp.codeshare.infrastructure.validators.infrastructure.*

internal class FullNameValidator : Validator<String?, String> {
    companion object {
        private val NAME_LENGTH_RANGE = IntRange(2, 26) //https://en.wikipedia.org/wiki/ISO/IEC_7813
    }

    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(FullNameValidationError.InputIsNull)
        if (!NAME_LENGTH_RANGE.contains(input.length)) return Invalid(FullNameValidationError.InvalidLength)
        return Valid(input)
    }
}

internal sealed class FullNameValidationError(override val details: String?) : IValidationError {
    object InputIsNull : FullNameValidationError("Input cannot be null.")
    object InvalidLength : FullNameValidationError("Input length is invalid")
}