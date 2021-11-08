package net.givtapp.codeshare.infrastructure

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal // Annotation makes sure this object can be used as a threadsafe singleton.
object CurrencyFormatter {
    var userLocale: CustomLocale? = null
        get() {
                if (field == null)
                    throw CurrencyFormatterException("UserLocale cannot be null!")
                else
                    return field!!
            }

    fun setUserLocale(locale: String) {
        this.userLocale = CustomLocale(locale)
    }

    fun getLocalFormat(value: Float, decimals: Boolean = true): String {
        if (value.isNaN())
            throw CurrencyFormatterException("Value isNaN")
        var formatted = DecimalFormat().format(value.toDouble(), if (decimals) 2 else 0, userLocale!!)
        formatted = reverseCurrencyAndAmountOrder(formatted)
        return formatted
    }

    fun getCurrencySymbol(): String {
        return DecimalFormat().getCurrencySymbol(userLocale!!).toString()
    }

    private fun reverseCurrencyAndAmountOrder(originalValue: String): String {
        // We ALWAYS want the net.givtapp.codeshare.currency symbols for Belgium and Germany at beginning
        if (arrayListOf("BE", "DE").contains(userLocale!!.country)) {
            var formattedValue = originalValue
            formattedValue = formattedValue.replace(getCurrencySymbol(), "")
            formattedValue = formattedValue.removeSuffix(Typography.nbsp.toString())
            formattedValue = "€${Typography.nbsp}$formattedValue"
            return formattedValue
        }
        return originalValue
    }
}

class CurrencyFormatterException(message: String) : Exception(message)