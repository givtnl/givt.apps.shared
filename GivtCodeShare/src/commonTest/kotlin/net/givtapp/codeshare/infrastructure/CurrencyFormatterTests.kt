package net.givtapp.codeshare.infrastructure

import net.givtapp.codeshare.infrastructure.formatters.CurrencyFormatter
import net.givtapp.codeshare.infrastructure.formatters.CurrencyFormatterException
import kotlin.test.Test
import kotlin.test.assertTrue

class CurrencyFormatterTests {
    val currencyFormatter = CurrencyFormatter
    @Test
    fun ensureCorrectExceptionIsThrownWhenLocaleIsEmpty() {
        var exceptionIsThrown = false
        try {
            currencyFormatter.getLocalFormat(1234.56f, true, "")
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
            currencyFormatter.getLocalFormat(1234.56f, true, "enGB")
        } catch(error: Exception) {
            isValid = error is CurrencyFormatterException
        }
        assertTrue { isValid }
    }

    @Test
    fun ensureValidLocaleDoesNotThrowWithValidLocal() {
        var isValid = true
        try {
        currencyFormatter.getLocalFormat(1234.56f,true, "en-GB")
        } catch(error: Exception) {
            isValid = false
        }
        assertTrue { isValid }
    }
}