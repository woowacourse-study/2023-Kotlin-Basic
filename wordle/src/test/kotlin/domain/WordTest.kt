package domain

import domain.LetterCompareResult.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

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

    test("동등성 테스트") {
        val word1 = Word("apple")
        val word2 = Word("apple")

        (word1 == word2) shouldBe true
    }

    test("compareWithCorrectAnswer 테스트") {
        forAll(
            row(Word("spill"), Word("hello"), listOf(GRAY, GRAY, YELLOW, GREEN, GRAY)),
            row(Word("spill"), Word("label"), listOf(YELLOW, GRAY, GRAY, GRAY, GREEN)),
            row(Word("spill"), Word("spell"), listOf(GREEN, GREEN, GRAY, GREEN, GREEN)),
            row(Word("spill"), Word("spill"), listOf(GREEN, GREEN, GREEN, GREEN, GREEN)),
            row(Word("covet"), Word("click"), listOf(GREEN, GRAY, GRAY, GRAY, GRAY)),
        ) { correctAnswer, submittedWord, expected ->
            submittedWord.compareWithCorrectAnswer(correctAnswer).values shouldContainExactly expected
        }
    }
})
