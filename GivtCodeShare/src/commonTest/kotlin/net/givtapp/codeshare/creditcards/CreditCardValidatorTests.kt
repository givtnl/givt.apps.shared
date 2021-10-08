package net.givtapp.codeshare.creditcards

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CreditCardValidatorTests {
    // Nog testen op letters
    // CreditCard testen
    @Test
    fun ensureCreditCardThatStartsWithTwoOrFiveHasLengthSixteen() {
        // Valid MasterCard credit cards
        assertTrue { CreditCardValidator().creditCardIsValid("2000 0000 0000 0000") }
        assertTrue { CreditCardValidator().creditCardIsValid("5000 0000 0000 0000") }
        // Invalid MasterCard credit cards
        assertFalse { CreditCardValidator().creditCardIsValid("200000000000000") }
        assertFalse { CreditCardValidator().creditCardIsValid("5000 0000 0000 000") }
        assertFalse { CreditCardValidator().creditCardIsValid("20000000000000000") }
        assertFalse { CreditCardValidator().creditCardIsValid("5000 0000 0000 00000") }
    }

    @Test
    fun ensureCreditCardsWithThirteenToNineteenDigitsValidate() {
        // Valid Unknown credit cards
        assertTrue { CreditCardValidator().creditCardIsValid("0000 0000 0000 0") }
        assertTrue { CreditCardValidator().creditCardIsValid("0000 0000 0000 00") }
        assertTrue { CreditCardValidator().creditCardIsValid("0000 0000 0000 000") }
        assertTrue { CreditCardValidator().creditCardIsValid("0000 0000 0000 0000") }
        assertTrue { CreditCardValidator().creditCardIsValid("0000 0000 0000 0000 0") }
        assertTrue { CreditCardValidator().creditCardIsValid("0000 0000 0000 0000 00") }
        assertTrue { CreditCardValidator().creditCardIsValid("0000 0000 0000 0000 000") }
    }
    @Test
    fun ensureCreditCardCannotBeNullOrWhiteSpaceOrEmpty() {
        assertFalse { CreditCardValidator().creditCardIsValid("    ") }
        assertFalse { CreditCardValidator().creditCardIsValid("") }
    }

    @Test
    fun ensureExpiryDateInThePastIsNotValid() {
        assertFalse { CreditCardValidator().expiryDateIsValid(9, 2021) }
    }
    @Test
    fun ensureExpiryDateAtThisMonthIsValid() {
        assertTrue { CreditCardValidator().expiryDateIsValid(10, 2021) }
    }
    @Test
    fun ensureExpiryDateValidatesWhenMonthAndYearAreInTheFuture() {
        assertTrue { CreditCardValidator().expiryDateIsValid(3, 2026) }
    }
}