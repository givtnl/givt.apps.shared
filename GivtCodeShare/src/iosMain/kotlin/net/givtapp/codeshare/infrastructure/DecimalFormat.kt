package net.givtapp.codeshare.infrastructure

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle
import platform.darwin.NSUInteger

actual class DecimalFormat {
    actual fun format(double: Double, decimals: Int, locale: String): String {
        val numberFormatter = NSNumberFormatter()
        numberFormatter.minimumFractionDigits = decimals.toULong()
        numberFormatter.maximumFractionDigits = decimals.toULong()
        numberFormatter.numberStyle = NSNumberFormatterCurrencyStyle
        numberFormatter.locale = NSLocale(locale)
        return numberFormatter.stringFromNumber(NSNumber(double))!!
    }
}