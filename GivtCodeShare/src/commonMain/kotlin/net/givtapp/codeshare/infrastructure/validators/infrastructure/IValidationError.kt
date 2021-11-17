package net.givtapp.codeshare.infrastructure.validators.infrastructure

/**
 * An error that occurred during validation in a [Validator]. These errors are provided to a [ValidationResult.Invalid]
 * instance and returned from a [Validator.validate] function. This allows for specific reasons as to why validation
 * failed.
 */
interface IValidationError {
    /**
     * Detailed information about what this [ValidationError] represents and what error occurred.
     */
    val details: String?
}