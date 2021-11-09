package net.givtapp.codeshare.infrastructure

object CurrencyFormatter {
    fun getLocalFormat(value: Float, decimals: Boolean = true, localeString: String): String {
        val userLocale = CustomLocale(localeString)

        if (value.isNaN())
            throw CurrencyFormatterException("Value isNaN")
        var formatted = DecimalFormat().format(value.toDouble(), if (decimals) 2 else 0, userLocale)
        formatted = reverseCurrencyAndAmountOrder(formatted, userLocale)
        return formatted
    }

    fun getCurrencySymbol(localeString: String): String {
        val userLocale = CustomLocale(localeString)
        return DecimalFormat().getCurrencySymbol(userLocale).toString()
    }

    private fun reverseCurrencyAndAmountOrder(originalValue: String, userLocale: CustomLocale): String {
        // We ALWAYS want the net.givtapp.codeshare.currency symbols for Belgium and Germany at beginning
        if (arrayListOf("BE", "DE").contains(userLocale.country)) {
            var formattedValue = originalValue
            formattedValue = formattedValue.replace(getCurrencySymbol(userLocale.localeString), "")
            formattedValue = formattedValue.removeSuffix(Typography.nbsp.toString())
            formattedValue = "€${Typography.nbsp}$formattedValue"
            return formattedValue
        }
        return originalValue
    }
}

class CurrencyFormatterException(message: String) : Exception(message)