package infrastructure

import net.givtapp.codeshare.infrastructure.DecimalFormat
import kotlin.test.Test
import kotlin.test.assertEquals

class DecimalFormatTests {
    @Test
    fun ensureCurrencyIsCorrectlyFormattedForTheNetherlands() {
        val expectedValue = "€${Typography.nbsp}1.234,56"
        val valueFromFormatter: String = DecimalFormat().format(1234.56, 2, "nl-NL")
        assertEquals(expectedValue, valueFromFormatter)
    }
}