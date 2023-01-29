package domain

import domain.LetterCompareResult.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import java.time.LocalDateTime

class GameTest : FunSpec({

    test("단어를 제출하면 LetterCompareResults를 반환한다.") {
        val wordList = WordList("HELLO\nMYSQL\nSPILL\nREACT")

        val game = Game(wordList, LocalDateTime.of(2021, 6, 21, 0, 0))
        val letterCompareResults = game.submitWord("HELLO")

        letterCompareResults.values shouldContainExactly listOf(
            GRAY, GRAY, YELLOW, GREEN, GRAY
        )
    }

    test("단어 리스트에 없는 단어를 제출하면 예외가 발생한다.") {
        val wordList = WordList("HELLO\nMYSQL\nSPILL\nREACT")

        val game = Game(wordList, LocalDateTime.of(2021, 6, 21, 0, 0))

        shouldThrow<IllegalArgumentException> {
            game.submitWord("MICRO")
        }
    }
})
