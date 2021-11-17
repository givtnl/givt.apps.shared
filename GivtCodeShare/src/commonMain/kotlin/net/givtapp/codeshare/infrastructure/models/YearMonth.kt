package net.givtapp.codeshare.infrastructure.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import net.givtapp.codeshare.infrastructure.extensions.current
import net.givtapp.codeshare.infrastructure.listeners.StringValueChangedListener
import kotlin.properties.Delegates

class YearMonth {
    internal lateinit var rawValueChangedListener: RawValueChangedListener

    var rawValue: String by Delegates.observable("") { _, oldValue, newValue ->
        rawValueChangedListener.onValueChanged(oldValue, newValue)
    }

    var month: Int? = null
    var year: Int? = null

    val formatted: String
        get()  {
            val formattedString = "${month.toString().padStart(2, '0')}/${year.toString().substring(2)}"
            return formattedString
        }

    internal class RawValueChangedListener(private val yearMonth: YearMonth) : StringValueChangedListener() {
        override fun onValueChanged(oldValue: String, newValue: String) {
            if (oldValue == newValue || newValue.filter { !it.isDigit() && it.toString() != "/" }.count() >= 1)
                return

            if (newValue.isNotEmpty() && newValue.contains("/")) {
                val splitted = newValue.split("/")
                yearMonth.month = splitted[0].toInt()
                val currentYearFirstTwoDigits = LocalDateTime.current.year.toString().substring(0, 2)
                yearMonth.year = "$currentYearFirstTwoDigits${splitted[1]}".toInt()
            }
        }
    }
}
