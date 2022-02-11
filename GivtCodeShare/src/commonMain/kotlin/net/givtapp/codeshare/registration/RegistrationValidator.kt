package net.givtapp.codeshare.registration

import net.givtapp.codeshare.infrastructure.validators.*
import net.givtapp.codeshare.infrastructure.validators.EmailValidator
import net.givtapp.codeshare.infrastructure.validators.NameValidator
import net.givtapp.codeshare.infrastructure.validators.PasswordValidator
import net.givtapp.codeshare.infrastructure.validators.PostalCodeValidator
import net.givtapp.codeshare.infrastructure.validators.infrastructure.isValid

class RegistrationValidator {
    var emailAddress: String = ""
    var phoneNumber: String = ""
    var password: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var postalCode: String = ""

    val isValidEmail: Boolean
        get () = EmailValidator().validate(emailAddress).isValid

    val isValidPhoneNumber: Boolean
        get () = PhoneNumberValidator().validate(phoneNumber).isValid

    val isValidPassword: Boolean
        get () = PasswordValidator().validate(password).isValid

    val isValidFirstName: Boolean
        get () = NameValidator().validate(firstName).isValid

    val isValidLastName: Boolean
        get () = NameValidator().validate(lastName).isValid

    val isValidFullName: Boolean
        get () = isValidFirstName && isValidLastName && FullNameValidator().validate("$firstName $lastName").isValid

    val isValidPostalCode: Boolean
        get() = PostalCodeValidator().invoke(postalCode).isValid

}