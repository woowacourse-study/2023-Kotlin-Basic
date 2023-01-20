package wordle.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WordsReadableTest {

    @Test
    fun `words를 읽는다`() {
        val wordsReadable = WordsReadable { listOf("hello", "world").map(::Word) }

        val actual = wordsReadable.read()

        assertEquals(actual.size, 2)
    }
}
