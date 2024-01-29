package co.cmedina.marvelcomics.domain.exception

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MessageExceptionTest {

    @Test
    fun testMessageException() {
        val errorMessage = "Test error message"
        val messageException = MessageException(errorMessage)
        assertEquals(errorMessage, messageException.message)
    }
}