package net.givtapp.codeshare.infrastructure.validators

import net.givtapp.codeshare.infrastructure.validators.infrastructure.*

internal class EmailValidator : Validator<String?, String> {
    override fun validate(input: String?): ValidationResult<String> {
        if (input == null) return Invalid(EmailValidationError.InputIsNull)

        if (!input.matches(RFC_5322_REGEX)) return Invalid(EmailValidationError.InvalidFormat)

        return Valid(input)
    }
    companion object {
        /**
         * A valid email address [Regex] pattern. This [Regex] should conform to the RFC5322 specification.
         *
         * This [Regex] pattern was adapted from the following StackOverflow answer:
         * https://stackoverflow.com/a/201378/1478764
         */
        private val RFC_5322_REGEX = Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")
    }
}

sealed class EmailValidationError(override val details: String? = null) : IValidationError {
    object InputIsNull : EmailValidationError("Input is not a valid Email Address because it is null.")
    object InvalidFormat : EmailValidationError("Input is not in a valid Email Address Format.")
}