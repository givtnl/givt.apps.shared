package net.givtapp.codeshare.infrastructure

import java.text.NumberFormat
import java.util.*

actual class DecimalFormat {
    actual fun format(double: Double, decimals: Int, locale: String): String {
        val localeLanguage = locale.split("-").first()
        val localeCountry = locale.split("-").last()

        val numberFormat = NumberFormat.getCurrencyInstance(Locale(localeLanguage, localeCountry))

        return numberFormat.format(double)
    }
}