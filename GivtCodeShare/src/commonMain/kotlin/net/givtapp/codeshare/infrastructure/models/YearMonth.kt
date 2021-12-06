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

    var month: Int? = null
    var year: Int? = null

    val formatted: String
        get() {
            return if (month != null && year != null)
                "${month.toString().padStart(2, '0')}/${year.toString().padStart(2, '0')}"
            else
                ""
        }

    internal class RawValueChangedListener(private val yearMonth: YearMonth) : StringValueChangedListener() {
        override fun onValueChanged(oldValue: String, newValue: String) {
            if (oldValue == newValue || newValue.filter { !it.isDigit() }.count() >= 1)
                return
            if (newValue.isNotEmpty()) {
                var currentValue = newValue
                if (currentValue.contains("/"))
                    currentValue = currentValue.replace("/","")
                when(currentValue.length) {
                    1, 2 -> yearMonth.month = newValue.toInt()
                    3 -> {
                        yearMonth.month = currentValue.take(2).toInt()
                        yearMonth.year = currentValue.takeLast(1).toInt()
                    }
                    4 -> {
                        yearMonth.month = currentValue.take(2).toInt()
                        yearMonth.year = currentValue.takeLast(2).toInt()
                    }
                }

            }
            else {
                yearMonth.month = null
                yearMonth.year = null
            }
        }
    }
}
