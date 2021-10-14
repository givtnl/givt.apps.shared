package net.givtapp.codeshare.creditcards

import net.givtapp.codeshare.registration.RegistrationValidator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegistrationValidatorTests {
    private val _registrationValidator = RegistrationValidator()

    @Test
    fun ensurePasswordValidationWorks() {
        _registrationValidator.password = "test123"
        assertFalse { _registrationValidator.isValidPassword }
        _registrationValidator.password = "Tt123"
        assertFalse { _registrationValidator.isValidPassword }
        _registrationValidator.password = "testTTT"
        assertFalse { _registrationValidator.isValidPassword }
        _registrationValidator.password = "Test123"
        assertTrue { _registrationValidator.isValidPassword }
    }
}