package net.givtapp.codeshare.infrastructure.models

import kotlinx.datetime.LocalDateTime
import net.givtapp.codeshare.infrastructure.extensions.current
import net.givtapp.codeshare.infrastructure.listeners.StringValueChangedListener
import kotlin.properties.Delegates

class YearMonth {
    internal lateinit var rawValueChangedListener: RawValueChangedListener

    var rawValue: String by Delegates.observable("") { _, oldValue, newValue ->
        rawValueChangedListener.onValueChanged(oldValue, newValue)
    }

    // used for validation on the dates
    var month: Int? = null
    var year: Int? = null

    // used for string validation
    var monthString: String? = null
    var yearString: String? = null

    val formatted: String
        get() {
            return if (month != null && monthString?.length == 2 && year == null) {
                "${monthString}/"
            } else if (month != null && year != null && yearString?.length == 1)
                "${monthString}/${yearString}"
            else if (month != null && year != null)
                "${monthString}/${yearString}"
            else if (month == null && year == null) {
                ""
            } else
                "$month"
        }

    internal class RawValueChangedListener(private val yearMonth: YearMonth) : StringValueChangedListener() {
        override fun onValueChanged(oldValue: String, newValue: String) {

            try {
                if (oldValue == newValue || newValue.filter { !it.isDigit() && it != '/' }.count() >= 1 || (newValue.contains('/') && newValue.indexOf('/') != 2))
                    return
                if (newValue.isNotEmpty()) {
                    var currentValue = newValue
                    when (currentValue.length) {
                        1, 2 -> {
                            yearMonth.monthString = newValue
                            yearMonth.month = newValue.toInt()
                        }
                        3 -> {
                            yearMonth.monthString = currentValue.take(2)
                            yearMonth.yearString = currentValue.takeLast(1)
                            yearMonth.month = currentValue.take(2).toInt()
                            yearMonth.year = currentValue.takeLast(1).toInt()
                        }
                        4 -> {
                            yearMonth.monthString = currentValue.take(2)
                            yearMonth.yearString = currentValue.takeLast(2)
                            yearMonth.month = currentValue.take(2).toInt()
                            yearMonth.year = currentValue.takeLast(2).toInt()
                        }
                    }

                } else {
                    yearMonth.month = null
                    yearMonth.year = null
                }
            } catch (exception: NumberFormatException) {
                return
            }
        }
    }
}
