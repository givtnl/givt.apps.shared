package net.givtapp.codeshare.currency

import net.givtapp.codeshare.infrastructure.DecimalFormat
import kotlin.math.roundToInt
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object CurrencyControl {
    private var paymentType: PaymentType = PaymentType.Undefined
    private var currencySymbol: String = ""
    private var decimalSeparator: String = ""

    fun setPaymentType(nativePaymentType: Int) {
        this.paymentType = PaymentType.fromInt(nativePaymentType)
    }

    fun setCurrencySymbol(currencySymbol: String) {
        this.currencySymbol = currencySymbol
    }

    fun setDecimalSeparator(decimalSeparator: String) {
        this.decimalSeparator = decimalSeparator
    }

    fun format(value: Float, vararg formats: CurrencyFormatType): String {
        return ""
    }
}

class CurrencyFormatter(vararg formats: CurrencyFormatType) {
    private var decimals = 2
    private var currencySymbol = "€"
    init {
        for (format in formats) {
            when(format) {
                CurrencyFormatType.WithoutCurrency -> currencySymbol = ""
                CurrencyFormatType.WithoutDecimals -> decimals = 0
            }
        }
    }
    private fun formatDecimals() {

    }
}
