package net.givtapp.codeshare.registration

import com.chrynan.validator.EmailValidator
import com.chrynan.validator.PhoneNumberValidator
import com.chrynan.validator.isValid

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