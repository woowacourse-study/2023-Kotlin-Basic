package wordle.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WordsReaderTest {

    @Test
    fun `words를 읽는다`() {
        val wordsReader = WordsReader { listOf("hello", "world").map(::Word) }

        val actual = wordsReader.read()

        assertEquals(actual.size, 2)
    }
}
