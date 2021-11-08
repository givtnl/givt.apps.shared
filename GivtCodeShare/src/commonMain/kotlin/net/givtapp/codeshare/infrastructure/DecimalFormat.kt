package net.givtapp.codeshare.infrastructure

expect class DecimalFormat() {
    fun format(double: Double, decimals: Int, locale: String): String
}