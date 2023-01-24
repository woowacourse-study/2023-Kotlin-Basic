package domain

import domain.LetterCompareResult.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import java.time.LocalDateTime

class GameTest : FunSpec({

    test("단어를 제출하면 LetterCompareResults를 반환한다.") {
        val wordList = WordList("HELLO\nMYSQL\nSPILL\nREACT")

        val game = Game(wordList)
        val letterCompareResults = game.submitWord("HELLO", LocalDateTime.of(2021, 6, 21, 0, 0))

        letterCompareResults.values shouldContainExactly listOf(
            GRAY, GRAY, YELLOW, GREEN, GRAY
        )
    }
})
