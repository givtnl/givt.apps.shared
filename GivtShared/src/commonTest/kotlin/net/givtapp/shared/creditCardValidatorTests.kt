package net.givtapp.shared

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CreditCardValidatorTests {

    @Test
    fun ensureCreditCardValidatesWithSixteenNumbersAndThreeSpaces() {
        assertTrue { CreditCardValidator().isValidCreditCardNumber("0000 0000 0000 0000") }
    }

    @Test
    fun ensureCreditCardValidatesWithSixteenNumbersAndZeroSpaces() {
        assertTrue { CreditCardValidator().isValidCreditCardNumber("0000000000000000") }
    }

    @Test
    fun ensureAmexCreditCardValidatesWithFifteenNumbersAndThreeSpaces() {
        assertTrue { CreditCardValidator().isValidCreditCardNumber("0000 0000 0000 000") }
    }

    @Test
    fun ensureAmexCreditCardValidatesWithFifteenNumbersAndZeroSpaces() {
        assertTrue { CreditCardValidator().isValidCreditCardNumber("000000000000000") }
    }

    @Test
    fun ensureCreditCardCannotBeEmpty() {
        assertFalse { CreditCardValidator().isValidCreditCardNumber("") }
    }

    @Test
    fun ensureCreditCardCannotBeOnlyWhiteSpace() {
        assertFalse { CreditCardValidator().isValidCreditCardNumber("    ") }
    }

    @Test
    fun ensureExpiryDateCannotBeEmpty() {
        assertFalse { CreditCardValidator().isValidExpiryDate("") }
    }

    @Test
    fun ensureExpiryDateCannotBeOnlyWhiteSpace() {
        assertFalse { CreditCardValidator().isValidExpiryDate("    ") }
    }

    @Test
    fun ensureExpiryDateMustAlwaysBeFourDigitsWithLeadingZeros() {
        assertFalse { CreditCardValidator().isValidExpiryDate("922") }
    }

    @Test
    fun ensureExpiryDateCantValidateInThePast() {
        assertFalse { CreditCardValidator().isValidExpiryDate("0921") }
    }

    @Test
    fun ensureExpiryDateValidatesWhenMonthAndYearAreEqualToThisMonthAndYear() {
        assertTrue { CreditCardValidator().isValidExpiryDate("1021") }
    }

    @Test
    fun ensureExpiryDateValidatesWhenMonthAndYearAreInTheFuture() {
        assertTrue { CreditCardValidator().isValidExpiryDate("0326") }
    }
}