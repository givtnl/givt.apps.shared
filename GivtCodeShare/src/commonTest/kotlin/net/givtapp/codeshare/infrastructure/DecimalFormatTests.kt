package net.givtapp.codeshare.infrastructure

import kotlin.test.Test
import kotlin.test.assertEquals

class DecimalFormatTests {
    var currencyFormatter: CurrencyFormatter = CurrencyFormatter
    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForTheNetherlands() {
        currencyFormatter.setUserLocale("nl-NL")
        val expectedValue = "€${Typography.nbsp}1.234,56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f))
        assertEquals(Typography.euro, currencyFormatter.getCurrencySymbol().first())
    }

    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForBelgium() {
        currencyFormatter.setUserLocale("nl-BE")
        val expectedValue = "€${Typography.nbsp}1.234,56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f))
        assertEquals(Typography.euro, currencyFormatter.getCurrencySymbol().first())
    }

    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForGermany() {
        currencyFormatter.setUserLocale("de-DE")
        val expectedValue = "€${Typography.nbsp}1.234,56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f))
        assertEquals(Typography.euro, currencyFormatter.getCurrencySymbol().first())
    }

    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForUnitedKingdom() {
        currencyFormatter.setUserLocale("en-GB")
        val expectedValue = "£1,234.56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f))
        assertEquals(Typography.pound, currencyFormatter.getCurrencySymbol().first())
    }

    @Test
    fun ensureCurrencyAndAmountAreCorrectlyFormattedForUnitedStates() {
        currencyFormatter.setUserLocale("en-US")
        val expectedValue = "$1,234.56"
        assertEquals(expectedValue, currencyFormatter.getLocalFormat(1234.56f))
        assertEquals(Typography.dollar, currencyFormatter.getCurrencySymbol().first())
    }
}