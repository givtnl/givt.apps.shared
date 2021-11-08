package currency

import net.givtapp.codeshare.currency.PaymentType
import kotlin.test.Test
import kotlin.test.assertTrue

class PaymentTypeTests {

    @Test
    fun ensurePaymentTypeIsCorrectlyMappedThroughStaticExtension() {
        val expected = PaymentType.SEPADirectDebit
        assertTrue { expected == PaymentType.fromInt(0) }
    }
}