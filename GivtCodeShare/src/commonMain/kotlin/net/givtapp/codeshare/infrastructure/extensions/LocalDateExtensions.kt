package net.givtapp.codeshare.infrastructure.extensions
import kotlinx.datetime.*
import net.givtapp.codeshare.infrastructure.models.YearMonth

fun LocalDate.Companion.getLastDayOfYearMonth(yearMonth: YearMonth): LocalDate {
    val year =
        if (yearMonth.year.toString().length == 2) {
            "20${yearMonth.year}"
        } else {
            yearMonth.year
        }
    return LocalDate(year.toString().toInt(), yearMonth.month!!, daysInMonth(yearMonth))
}

fun LocalDate.Companion.daysInMonth(yearMonth: YearMonth): Int {
    val startDateOfMonth = LocalDate(yearMonth.year!!, yearMonth.month!!, 1)
    val startDateOfNextMonth =
        if (yearMonth.month == 12) // A new year
            LocalDate(yearMonth.year!! + 1, 1, 1)
        else
            LocalDate(yearMonth.year!!, yearMonth.month!! +1, 1)
    return startDateOfMonth.daysUntil(startDateOfNextMonth)
}