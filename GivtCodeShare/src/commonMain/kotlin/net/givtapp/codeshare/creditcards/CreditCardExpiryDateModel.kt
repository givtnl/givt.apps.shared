package net.givtapp.codeshare.creditcards

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import net.givtapp.codeshare.extensions.getLastDayOfYearMonth
import net.givtapp.codeshare.models.YearMonth

class CreditCardExpiryDateModel : YearMonth() {
    val lastDayOfYearMonthDate: LocalDate
        get() = LocalDate.getLastDayOfYearMonth(this)

    override var year: Int?
        get() = super.year
        set(value) {
            if (value != null && value.toString().count() == 2) {
                val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                val yearPrefix: String = now.year.toString().substring(0, 2)
                super.year = "$yearPrefix${value}".toInt()
            }
        }

    fun setValue(inputString: String) {
        val value = inputString.replace("/", "")
        if (value.count() <= 2)
            month = value.toInt()
        if (value.count() >= 3) {
            month = value.substring(0, 2).toInt()
            year = value.substring(2).toInt()
        }
    }

    val formatted: String
        get() {
            if (year == null)
                return "$month"
            return "${month.toString().padStart(2, '0')}/${year.toString().substring(2)}"
        }
}