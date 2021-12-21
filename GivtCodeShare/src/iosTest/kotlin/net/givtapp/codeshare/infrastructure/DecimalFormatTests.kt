package net.givtapp.codeshare.infrastructure

import net.givtapp.codeshare.infrastructure.formatters.DecimalFormat
import net.givtapp.codeshare.infrastructure.models.CustomLocale
import kotlin.test.Test
import kotlin.test.assertTrue

@Test
fun DecimalFormatTests.ensureCurrencyIsCorrectlyFormatted() {
    val expected = "€ 1.230,50"
    val input: Double = 1230.50
    val returnValue = DecimalFormat().format(input, 2, CustomLocale("nl-NL"))
    print(returnValue)
    assertTrue { expected == returnValue }
}
