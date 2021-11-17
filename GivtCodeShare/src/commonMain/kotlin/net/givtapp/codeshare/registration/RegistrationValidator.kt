package net.givtapp.codeshare.registration

import net.givtapp.codeshare.infrastructure.validators.EmailValidator
import net.givtapp.codeshare.infrastructure.validators.PasswordValidator
import net.givtapp.codeshare.infrastructure.validators.PhoneNumberValidator
import net.givtapp.codeshare.infrastructure.validators.infrastructure.isValid

class RegistrationValidator {
    var emailAddress: String = ""
    var phoneNumber: String = ""
    var password: String = ""

    val isValidEmail: Boolean
        get () = EmailValidator().validate(emailAddress).isValid

    val isValidPhoneNumber: Boolean
        get () = PhoneNumberValidator().validate(phoneNumber).isValid

    val isValidPassword: Boolean
        get () = PasswordValidator().validate(password).isValid
}