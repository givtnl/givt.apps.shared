package net.givtapp.codeshare.infrastructure.formatters

import net.givtapp.codeshare.infrastructure.models.CustomLocale
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

actual class DecimalFormat {
    actual fun format(double: Double, decimals: Int, locale: CustomLocale): String {
        val userLocale = Locale(locale.language, locale.country)
        val numberFormat = NumberFormat.getCurrencyInstance(userLocale)
        return numberFormat.format(double)
    }
    actual fun getCurrencySymbol(locale: CustomLocale): Char {
        val userLocale = Locale(locale.language, locale.country)
//        val numberFormat = NumberFormat.getCurrencyInstance(userLocale)
        val decimalFormatSymbols = DecimalFormatSymbols.getInstance(userLocale)
//            decimalFormatSymbols.net.givtapp.codeshare.currency = numberFormat.net.givtapp.codeshare.currency
        return decimalFormatSymbols.currencySymbol.toCharArray().first()
    }
}