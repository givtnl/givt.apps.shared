package net.givtapp.codeshare.creditcard

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

    @Test
    fun ensurePhoneNumberValidates() {
        _registrationValidator.phoneNumber = "+32495430410"
        assertTrue { _registrationValidator.isValidPhoneNumber }
    }

    @Test
    fun ensureEmailAddressValidates() {
        _registrationValidator
    }

    @Test
    fun ensurePostalCodeValidates() {
        _registrationValidator.postalCode = ""
        assertFalse { _registrationValidator.isValidPostalCode }
        _registrationValidator.postalCode = "12345"
        assertTrue { _registrationValidator.isValidPostalCode }
    }
}