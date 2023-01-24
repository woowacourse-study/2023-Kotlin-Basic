package domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class AnswerMarkingsTest {

    @DisplayName("알파벳을 포함하지 않는 경우 true 반환")
    @Test
    fun doesNotContain() {
        val answerMarkings = AnswerMarkings(
                mutableListOf(CharAndIsMarked('a'), CharAndIsMarked('b', true), CharAndIsMarked('c'))
        )

        assertTrue(answerMarkings.doesNotContain('d'))
        assertTrue(answerMarkings.doesNotContain('b'))
    }

    @DisplayName("알파벳을 포함하지 않는 경우 false 반환")
    @Test
    fun contain() {
        val answerMarkings = AnswerMarkings(
                mutableListOf(CharAndIsMarked('a'), CharAndIsMarked('b', true), CharAndIsMarked('c'))
        )

        assertFalse(answerMarkings.doesNotContain('a'))
    }
}
