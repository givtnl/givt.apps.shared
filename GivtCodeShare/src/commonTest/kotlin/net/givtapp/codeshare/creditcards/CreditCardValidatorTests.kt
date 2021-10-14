package net.givtapp.codeshare.creditcards

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CreditCardValidatorTests {
    private val _creditCardValidator = CreditCardValidator()

    @Test
    fun ensureCompanyGetterWorksAsExpected() {
        _creditCardValidator.creditCard.number = "5"
        assertTrue { _creditCardValidator.creditCard.company == CreditCardCompany.Mastercard }
    }
    @Test
    fun ensureBadCreditCardNumberIsInvalid() {
        assertTrue {
            _creditCardValidator.creditCard.number = "5555341244441115"
            _creditCardValidator.cardNumberIsValid()
        }
        assertTrue {
            _creditCardValidator.creditCard.number = "2222410700000002"
            _creditCardValidator.cardNumberIsValid()
        }
        assertFalse {
            _creditCardValidator.creditCard.number = "555534124444111"
            _creditCardValidator.cardNumberIsValid()
        }
        assertFalse {
            _creditCardValidator.creditCard.number = "222241070000000"
            _creditCardValidator.cardNumberIsValid()
        }
    }

    @Test
    fun ensureSecurityCodeValidates() {
        // Amex
        _creditCardValidator.creditCard.number = "371449635398431"
        _creditCardValidator.creditCard.securityCode = 7373
        assertTrue { _creditCardValidator.securityCodeIsValid()}
        // MasterCard
        _creditCardValidator.creditCard.number = "5555555555554444"
        _creditCardValidator.creditCard.securityCode = 737
        assertTrue { _creditCardValidator.securityCodeIsValid()}

        // Visa
        _creditCardValidator.creditCard.number = "4917610000000000"
        _creditCardValidator.creditCard.securityCode = 737
        assertTrue { _creditCardValidator.securityCodeIsValid()}

        // Discovery
        _creditCardValidator.creditCard.number = "6011111111111117"
        _creditCardValidator.creditCard.securityCode = 737
        assertTrue { _creditCardValidator.securityCodeIsValid()}

        // Undefined
        _creditCardValidator.creditCard.number = "000000000000000"
        _creditCardValidator.creditCard.securityCode = 1234
        assertTrue { _creditCardValidator.securityCodeIsValid()}

        _creditCardValidator.creditCard.number = "000000000000000000000"
        _creditCardValidator.creditCard.securityCode = 123
        assertTrue { _creditCardValidator.securityCodeIsValid()}

    }

    @Test
    fun ensureUndefinedCompanyCreditCardsValidate() {
        val validUndefinedNumbers = arrayOf(
            "0000000000000",
            "00000000000000",
            "000000000000000",
            "0000000000000000",
            "00000000000000000",
            "000000000000000000",
            "0000000000000000000"
        )
        validUndefinedNumbers.forEach {
            _creditCardValidator.creditCard.number = it
            assertTrue {
                _creditCardValidator.cardNumberIsValid()
            }
        }
    }

    @Test
    fun ensureCreditCardCannotBeNullOrWhiteSpaceOrEmpty() {
        assertFalse {
            _creditCardValidator.creditCard.number = "     "
            _creditCardValidator.cardNumberIsValid()
        }
        assertFalse {
            _creditCardValidator.creditCard.number = " "
            _creditCardValidator.cardNumberIsValid()
        }
    }

    @Test
    fun ensureExpiryDateCannotBeInThePast() {
        _creditCardValidator.creditCard.expiryDate.month = 9
        _creditCardValidator.creditCard.expiryDate.month = 2021
        assertFalse { _creditCardValidator.expiryDateIsValid() }
    }

    @Test
    fun ensureExpiryDateAtThisMonthIsValid() {
        _creditCardValidator.creditCard.expiryDate.month = _creditCardValidator.localDate.monthNumber
        _creditCardValidator.creditCard.expiryDate.month = _creditCardValidator.localDate.year
        assertTrue { _creditCardValidator.expiryDateIsValid() }
    }

    @Test
    fun ensureExpiryDateValidatesWhenMonthAndYearAreInTheFuture() {
        _creditCardValidator.creditCard.expiryDate.month = 1
        _creditCardValidator.creditCard.expiryDate.year = _creditCardValidator.localDate.year + 1
        assertTrue { _creditCardValidator.expiryDateIsValid() }
    }
}