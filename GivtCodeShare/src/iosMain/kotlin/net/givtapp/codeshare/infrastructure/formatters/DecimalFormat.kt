package net.givtapp.codeshare.infrastructure.formatters

import net.givtapp.codeshare.infrastructure.models.CustomLocale

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle

actual class DecimalFormat {
    actual fun format(double: Double, decimals: Int, locale: CustomLocale): String {
        val numberFormatter = NSNumberFormatter()
        numberFormatter.minimumFractionDigits = decimals.toULong()
        numberFormatter.maximumFractionDigits = decimals.toULong()
        numberFormatter.numberStyle = NSNumberFormatterCurrencyStyle
        numberFormatter.locale = NSLocale(locale.toString())
        return numberFormatter.stringFromNumber(NSNumber(double))!!
    }

    actual fun getCurrencySymbol(locale: CustomLocale): Char {
        val numberFormatter = NSNumberFormatter()
        numberFormatter.locale = NSLocale(locale.toString())
        return numberFormatter.currencySymbol.toCharArray().first()
    }
}