package domain

import domain.LetterCompareResult.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly

class WordTest : FunSpec({

    test("글자 개수가 5개가 아니라면, 예외를 던진다.") {
        shouldThrow<IllegalArgumentException> {
            Word("ABCDEFG")
        }
    }

    test("주 생성자로 넣은 단어가 Letter List로 변환된다.") {
        val word = Word("HELLO")
        val letters = word.letters

        letters.map { it.letter } shouldContainExactly listOf(
            "H", "E", "L", "L", "O"
        )
    }

    context("compareWithCorrectAnswer 테스트 (정답: SPILL)") {
        val correctAnswer = Word("SPILL")

        test("입력: HELLO") {
            val word = Word("HELLO")
            val actual = word.compareWithCorrectAnswer(correctAnswer)

            actual shouldContainExactly listOf(
                GRAY, GRAY, YELLOW, GREEN, GRAY
            )
        }

        test("입력: LABEL") {
            val word = Word("LABEL")
            val actual = word.compareWithCorrectAnswer(correctAnswer)

            actual shouldContainExactly listOf(
                YELLOW, GRAY, GRAY, GRAY, GREEN
            )
        }

        test("입력: SPELL") {
            val word = Word("SPELL")
            val actual = word.compareWithCorrectAnswer(correctAnswer)

            actual shouldContainExactly listOf(
                GREEN, GREEN, GRAY, GREEN, GREEN
            )
        }

        test("입력: SPILL") {
            val word = Word("SPILL")
            val actual = word.compareWithCorrectAnswer(correctAnswer)

            actual shouldContainExactly listOf(
                GREEN, GREEN, GREEN, GREEN, GREEN
            )
        }
    }
})
