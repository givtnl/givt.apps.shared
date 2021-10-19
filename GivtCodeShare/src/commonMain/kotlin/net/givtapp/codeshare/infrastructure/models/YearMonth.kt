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

    internal var month: Int? = null
    internal var year: Int? = null

    val formatted: String
        get() = "${month.toString().padStart(2, '0')}/${year.toString().substring(2)}"

    internal class RawValueChangedListener(private val yearMonth: YearMonth) : StringValueChangedListener() {
        override fun onValueChanged(oldValue: String, newValue: String) {
            if (oldValue == newValue || newValue.filter { !it.isDigit() }.count() >= 1)
                return

            if (newValue.isNotEmpty()) {
                val monthPlaceholder = StringBuilder()
                val yearPlaceholder = StringBuilder()
                charLoop@ for (char in newValue) {
                    monthLoop@ while (monthPlaceholder.length < 2) {
                        if (monthPlaceholder.isEmpty()) {
                            if (IntRange(0, 1).contains(char.digitToInt())) {
                                monthPlaceholder.append(char)
                            } else if (IntRange(2, 9).contains(char.digitToInt())) {
                                monthPlaceholder.append("0${char}")
                            }
                            yearMonth.month = monthPlaceholder.toString().toInt()
                            continue@charLoop
                        } else if (monthPlaceholder.length == 1) {
                            if (monthPlaceholder.first().digitToInt() == 1 && IntRange(0, 2).contains(char.digitToInt())) {
                                monthPlaceholder.append(char)
                            } else if (monthPlaceholder.first().digitToInt() == 0 && IntRange(1, 9).contains(char.digitToInt())) {
                                monthPlaceholder.append(char)
                            } else {
                                return
                            }
                            yearMonth.month = monthPlaceholder.toString().toInt()
                            continue@charLoop

                        }
                    }

                    if (yearPlaceholder.isEmpty())
                        yearPlaceholder.append(LocalDateTime.current.date.toString().substring(0, 2))

                    yearWhile@ while (yearPlaceholder.length < 4) {
                        yearPlaceholder.append(char)
                        yearMonth.year = yearPlaceholder.toString().toInt()
                        continue@charLoop
                    }
                }
            }
        }
    }
}
