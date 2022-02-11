package net.givtapp.codeshare.creditcard

import net.givtapp.codeshare.registration.RegistrationValidator
import kotlin.test.Test
import kotlin.test.assertEquals
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

    @Test
    fun ensureFullNameFailsWhenEitherIsEmpty() {
        _registrationValidator.firstName = "Mike"
        _registrationValidator.lastName = ""
        assertFalse { _registrationValidator.isValidFullName }
        _registrationValidator.firstName = ""
        _registrationValidator.lastName = "Pattyn"
        assertFalse { _registrationValidator.isValidFullName }
    }

    @Test
    fun ensureFullNameLengthValidationWorks() {
        _registrationValidator.firstName = "Alexander" //9
        _registrationValidator.lastName = "Van den Berghe" //14
        assertTrue { _registrationValidator.isValidFullName } //23 + 1 spatie
        _registrationValidator.firstName = "Alexander1234" //13
        _registrationValidator.lastName = "Van den Berghe" //14
        assertFalse { _registrationValidator.isValidFullName } //27 + 1 spatie
        _registrationValidator.firstName = "Alexander" // 9
        _registrationValidator.lastName = "Van den Berghe1234" //18
        assertFalse { _registrationValidator.isValidFullName } //27 + 1 spatie
        _registrationValidator.firstName = "1234567890" //10
        _registrationValidator.lastName = "123456789012345" //15
        assertTrue { _registrationValidator.isValidFullName } // 25 + 1 spatie moet werken
        _registrationValidator.firstName = "12345678901" //11
        _registrationValidator.lastName = "123456789012345" //15
        assertFalse { _registrationValidator.isValidFullName } // 26 + 1 spatie mag niet werken
    }
}