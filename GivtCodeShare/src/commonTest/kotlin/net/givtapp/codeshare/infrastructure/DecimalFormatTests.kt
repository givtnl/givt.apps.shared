package net.givtapp.codeshare.infrastructure

import net.givtapp.codeshare.infrastructure.formatters.CurrencyFormatter
import kotlin.test.Test
import kotlin.test.assertEquals

class DecimalFormatTests {
    var currencyFormatter: CurrencyFormatter = CurrencyFormatter
    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForTheNetherlands() {
        val expectedValue = "€${Typography.nbsp}1.234,56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f, true, "nl-NL"))
        assertEquals(Typography.euro, currencyFormatter.getCurrencySymbol("nl-NL").first())
    }

    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForBelgium() {
        val expectedValue = "€${Typography.nbsp}1.234,56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f, true, "nl-BE"))
        assertEquals(Typography.euro, currencyFormatter.getCurrencySymbol("nl-BE").first())
    }

    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForGermany() {
        val expectedValue = "€${Typography.nbsp}1.234,56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f, true, "de-DE"))
        assertEquals(Typography.euro, currencyFormatter.getCurrencySymbol("de-DE").first())
    }

    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForUnitedKingdom() {
        val expectedValue = "£1,234.56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f, true, "en-GB"))
        assertEquals(Typography.pound, currencyFormatter.getCurrencySymbol("en-GB").first())
    }

    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForUnitedStates() {
        val expectedValue = "$1,234.56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f, true, "en-US"))
        assertEquals(Typography.dollar, currencyFormatter.getCurrencySymbol("en-US").first())
    }
}