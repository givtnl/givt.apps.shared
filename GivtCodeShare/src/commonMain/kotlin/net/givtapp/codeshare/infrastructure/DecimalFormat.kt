package net.givtapp.codeshare.infrastructure

expect class DecimalFormat() {
    fun format(double: Double, decimals: Int, locale: CustomLocale): String
    fun getCurrencySymbol(locale: CustomLocale): Char
}