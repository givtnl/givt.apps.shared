package net.givtapp.codeshare.registration

import net.givtapp.codeshare.infrastructure.validators.EmailValidator
import net.givtapp.codeshare.infrastructure.validators.NameValidator
import net.givtapp.codeshare.infrastructure.validators.PasswordValidator
import net.givtapp.codeshare.infrastructure.validators.PhoneNumberValidator
import net.givtapp.codeshare.infrastructure.validators.infrastructure.isValid

class RegistrationValidator {
    var emailAddress: String = ""
    var phoneNumber: String = ""
    var password: String = ""
    var firstName: String = ""
    var lastName: String = ""

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

}

class RegistrationFlowController {
    var currentFlow: RegFlowType? = null
    val currentFlowSteps: Array<RegFlowStep>
        get () {
            var array = arrayOf<RegFlowStep>()
            if (currentFlow != null) {
                array = FlowSteps[currentFlow] ?: array
            }
            return array
        }

}

enum class RegFlowType {
    US
}
enum class RegFlowStep {
    emailPhoneNumberPasswordEntered,
    creditCardDetailsEntered,
    firstNameLastNameEntered
}

val RegistrationFlowController.FlowSteps: HashMap<RegFlowType, Array<RegFlowStep>>
    get() {
        return hashMapOf(
            RegFlowType.US to arrayOf(
                RegFlowStep.creditCardDetailsEntered,
                RegFlowStep.emailPhoneNumberPasswordEntered,
                RegFlowStep.firstNameLastNameEntered)
        )
    }