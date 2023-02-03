package wordle.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WordsTest {

    @Test
    fun `사전에 존재하는 단어인 경우 예외를 던지지 않는다`() {
        val words = Words(listOf(Word("hello"), Word("world")))

        assertDoesNotThrow() { words.validateNonExist(Word("hello")) }
    }

    @Test
    fun `사전에 존재하지 않는 단어인 경우 예외를 던진다`() {
        val words = Words(listOf(Word("hello"), Word("world")))

        assertThrows<IllegalArgumentException> { words.validateNonExist(Word("heath")) }
    }
}
