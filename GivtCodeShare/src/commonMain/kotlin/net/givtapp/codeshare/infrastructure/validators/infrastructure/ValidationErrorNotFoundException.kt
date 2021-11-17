package net.givtapp.codeshare.infrastructure.validators.infrastructure

/**
 * A [RuntimeException] that indicates there was an error asserting that a [ValidationResult] contained a
 * [ValidationError].
 *
 * This exception is thrown from the [assertContains] function.
 */
class ValidationErrorNotFoundException(error: IValidationError)
    : RuntimeException("ValidationErrorNotFoundException: error = $error")