package net.givtapp.codeshare.currency

enum class PaymentType(val value: Int) {
    Undefined(-1),
    SEPADirectDebit(0),
    BACSDirectDebit(1),
    CreditCard(2);

    companion object {
        fun fromInt(value: Int): PaymentType {
            if (!values().any { it.value == value })
                return Undefined
            return values().first{
                it.value == value
            }
        }
    }
}