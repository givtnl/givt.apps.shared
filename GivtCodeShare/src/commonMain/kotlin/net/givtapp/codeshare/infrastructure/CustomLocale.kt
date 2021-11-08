package net.givtapp.codeshare.infrastructure

// CustomLocale is a representation of a Locale by language and country containing
// Contains 2 char, seperator, 2 char. EG: nl-BE

data class CustomLocale(val localeString: String) {
    val language: String
    val country: String
    init {
        if (!localeString.matches(Regex("[a-z]{2}-[A-Z]{2}$")))
            throw CurrencyFormatterException("Cannot convert value ${localeString} to Locale")
        val localeStrings = localeString.split("-")
        language = localeStrings.first()
        country = localeStrings.last()
    }
    override fun toString(): String {
        return "${language}-${country}"
    }
}