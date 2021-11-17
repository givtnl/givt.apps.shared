package net.givtapp.codeshare.infrastructure.formatters

import net.givtapp.codeshare.infrastructure.models.CustomLocale

expect class DecimalFormat() {
    fun format(double: Double, decimals: Int, locale: CustomLocale): String
    fun getCurrencySymbol(locale: CustomLocale): Char
}