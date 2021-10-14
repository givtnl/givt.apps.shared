package net.givtapp.codeshare.registration

import com.chrynan.validator.EmailValidator
import com.chrynan.validator.PhoneNumberValidator
import com.chrynan.validator.isValid

class RegistrationValidator {
    lateinit var emailAddress: String
    lateinit var phoneNumber: String
    lateinit var password: String

    val isValidEmail: Boolean
        get () = EmailValidator().validate(emailAddress).isValid

    val isValidPhoneNumber: Boolean
        get () {
            val isValid = PhoneNumberValidator().validate(phoneNumber).isValid
            return isValid
        }

    val isValidPassword: Boolean
        get () {
            val isValid = PasswordValidator().validate(password).isValid
            return isValid
        }

    val isValidRegistrationData: Boolean
        get () = isValidEmail && isValidPassword && isValidPhoneNumber
}