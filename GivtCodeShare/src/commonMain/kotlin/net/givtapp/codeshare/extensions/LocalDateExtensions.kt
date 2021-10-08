package net.givtapp.codeshare.extensions
import kotlinx.datetime.*


fun LocalDate.Companion.daysInMonth(month: Int, year: Int): Int {
    val startDateOfMonth = LocalDate(year, month, 1)
    val startDateOfNextMonth =
        if (month == 12) // A new year
            LocalDate(year + 1, 1, 1)
        else
            LocalDate(year, month +1, 1)
    return startDateOfMonth.daysUntil(startDateOfNextMonth)
}