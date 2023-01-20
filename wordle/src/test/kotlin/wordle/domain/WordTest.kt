package wordle.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WordTest {

    @Test
    fun `단어를 생성한다`() {
        val word = "hello"

        val actual = Word(word)

        assertEquals(actual.value, word)
    }

    @Test
    fun `단어의 길이가 5글자가 아닌 경우 예외를 던진다`() {
        val word = "wordle"

        assertThrows<IllegalArgumentException> { Word(word) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["    ", "a    ", "12345", "ㄱㄴㄷㄹㅁ"])
    fun `단어에 알파벳이 아닌 것이 포함된 경우 예외를 던진다`(word: String) {
        assertThrows<IllegalArgumentException> { Word(word) }
    }
}
