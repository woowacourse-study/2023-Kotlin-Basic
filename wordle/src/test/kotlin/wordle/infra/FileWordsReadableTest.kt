package wordle.infra

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FileWordsReadableTest {

    @Test
    fun `파일에서 words를 읽는다`() {
        val fileWordsReadable = FileWordsReadable()

        val actual = fileWordsReadable.read()

        assertEquals(actual.size, 2309)
        println(actual)
    }
}
