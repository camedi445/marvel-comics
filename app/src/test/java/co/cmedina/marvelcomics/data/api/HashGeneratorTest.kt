package co.cmedina.marvelcomics.data.api

import io.mockk.every
import io.mockk.mockkObject
import org.junit.Test

class HashGeneratorTest {

    @Test
    fun testGenerateHash() = mockkObject(TimeHelper) {
        every { TimeHelper.getNow() } returns 123456789L
        val result = generateHash()
        result.let { (timestamp, hash) ->
            assert(timestamp == "123456789")
            assert(hash == "7480aff047eae0543923ed1f0749bfad")
        }
    }
}