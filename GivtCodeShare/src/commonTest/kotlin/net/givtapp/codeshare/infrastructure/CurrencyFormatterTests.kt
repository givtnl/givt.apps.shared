package net.givtapp.codeshare.infrastructure

import kotlin.test.Test
import kotlin.test.assertTrue

class CurrencyFormatterTests {
    val currencyFormatter = CurrencyFormatter
    @Test
    fun ensureCorrectExceptionIsThrownWhenLocaleIsEmpty() {
        var exceptionIsThrown = false
        try {
            currencyFormatter.userLocale = null
            currencyFormatter.getLocalFormat(1234.56f)
        } catch(error: Exception) {
            when(error) {
                is CurrencyFormatterException -> exceptionIsThrown = true
            }
        }
        assertTrue { exceptionIsThrown }
    }

    @Test
    fun ensureCorrectExceptionIsThrownWhenLocaleFormatIsWrong() {
        var isValid = false
        try {
            currencyFormatter.setUserLocale("enGB")
            currencyFormatter.getLocalFormat(1234.56f)
        } catch(error: Exception) {
            isValid = error is CurrencyFormatterException
        }
        assertTrue { isValid }
    }

    @Test
    fun ensureValidLocaleDoesNotThrowWithValidLocal() {
        var isValid = true
        try {
        currencyFormatter.setUserLocale("en-GB")
        currencyFormatter.getLocalFormat(1234.56f)
        } catch(error: Exception) {
            isValid = false
        }
        assertTrue { isValid }
    }
}