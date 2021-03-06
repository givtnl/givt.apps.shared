package net.givtapp.codeshare.infrastructure.validators.infrastructure

import kotlin.reflect.KClass

/**
 * A [RuntimeException] that indicates there was an error asserting that a [ValidationResult] was valid or invalid.
 *
 * This exception is thrown from the [assertValid] and [assertInvalid] functions.
 */
class ValidationException internal constructor(
    message: String? = null
):RuntimeException(message)

/**
 * A creator function for the [ValidationException] class.
 */
@Suppress("FunctionName")
fun <T, E: ValidationResult<*>> ValidationException(
    message: String? = null,
    result: ValidationResult<T>,
    expected: KClass<E>
): ValidationException {
    val fullMessage = buildString {
        append("ValidationException: Unexpected ValidationResult: expected = $expected; actual = ${result::class}\n")
        append("result = $result\n")

        if (message != null) {
            append("message = $message\n")
        }
    }
    return ValidationException(fullMessage)
}