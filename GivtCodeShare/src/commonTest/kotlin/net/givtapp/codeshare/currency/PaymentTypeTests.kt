package net.givtapp.codeshare.currency

import kotlin.test.Test
import kotlin.test.assertTrue

class PaymentTypeTests {

    @Test
    fun ensurePaymentTypeIsCorrectlyMappedThroughStaticExtension() {
        val expected = PaymentType.SEPADirectDebit
        assertTrue { expected == PaymentType.fromInt(0) }
    }
}