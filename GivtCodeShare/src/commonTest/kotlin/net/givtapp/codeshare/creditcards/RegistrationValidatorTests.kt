package net.givtapp.codeshare.creditcards

import net.givtapp.codeshare.registration.RegistrationValidator
import kotlin.test.Test
import kotlin.test.assertTrue

class RegistrationValidatorTests {
    private val _registrationValidator = RegistrationValidator()

    @Test
    fun ensureValidationWorks() {
        _registrationValidator.emailAddress = "testen@givtapp.net"
        _registrationValidator.phoneNumber = "+32495430410"
        _registrationValidator.password = "Test123"
        assertTrue { _registrationValidator.isValidRegistrationData }
    }
}