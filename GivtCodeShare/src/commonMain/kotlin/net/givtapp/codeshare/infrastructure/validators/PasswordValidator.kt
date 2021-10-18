package net.givtapp.codeshare.infrastructure.validators

import com.chrynan.validator.*

internal class PasswordValidator : Validator<String?, String> {
    companion object {
        private val PASSWORD_REGEX = Regex(".*[0-9]+.*[A-Z]+.*|.*[A-Z]+.*[0-9]+.*")
        private val PASSWORD_RANGE = IntRange(7, 35)
    }
    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(PasswordValidationError.InputIsNull)
        if (!PASSWORD_RANGE.contains(input.length)) return Invalid(PasswordValidationError.InvalidLength)
        if (!input.matches(PASSWORD_REGEX)) return Invalid(PasswordValidationError.InvalidFormat)
        return Valid(input)
    }
}

internal sealed class PasswordValidationError(override val details: String?) : ValidationError {
    object InputIsNull : PasswordValidationError("Input is not a valid Password because it is null.")
    object InvalidFormat : PasswordValidationError("Input is not in a valid Password format.")
    object InvalidLength : PasswordValidationError("Input length is invalid")
}