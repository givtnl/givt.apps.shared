package net.givtapp.codeshare.creditcards
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
class CreditCardTests {
    @Test
    fun ensureNoExceptionWithInvalidArguments() {
        var isValid = true
        try {
            CreditCard("saafdf")
        } catch(error: Error) {
            isValid = false
        }
        assertTrue { isValid }
    }
}