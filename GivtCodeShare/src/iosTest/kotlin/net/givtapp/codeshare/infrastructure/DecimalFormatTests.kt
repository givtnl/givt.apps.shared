package net.givtapp.codeshare.infrastructure

import net.givtapp.codeshare.infrastructure.formatters.DecimalFormat
import kotlin.test.Test
import kotlin.test.assertTrue

class DecimalFormatTests {
    @Test
    fun ensureCurrencyIsCorrectlyFormatted() {
        val expected = "€ 1.230,50"
        val input: Double = 1230.50
        val returnValue = DecimalFormat().format(input, 2, "nl-NL")
        print(returnValue)
        assertTrue { expected == returnValue }
    }
}