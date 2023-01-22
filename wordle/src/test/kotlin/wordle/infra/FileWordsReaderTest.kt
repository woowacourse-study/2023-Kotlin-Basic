package wordle.infra

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FileWordsReaderTest {

    @Test
    fun `파일에서 words를 읽는다`() {
        val wordsReader = FileWordsReader()

        val actual = wordsReader.read()

        assertEquals(actual.size, 2309)
    }
}
