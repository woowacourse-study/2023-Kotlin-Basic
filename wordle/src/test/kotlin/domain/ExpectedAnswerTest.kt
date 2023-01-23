package domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ExpectedAnswerTest {

    @DisplayName("단어 리스트에 존재하지 않는 단어일 경우 예외 발생")
    @Test
    fun ifNotExistedWordThenThrowException() {
        val notExistedWord = "존재하지 않는 단어"

        assertThrows<IllegalArgumentException> { ExpectedAnswer(notExistedWord) }
    }
}
