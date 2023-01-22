package wordle.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class JudgementTest {

    @ParameterizedTest
    @CsvSource(
        "world, weary, 🟩⬜⬜🟨⬜",
        "rebut, speed, ⬜⬜🟨⬜⬜",
        "erase, speed, 🟨⬜🟨🟨⬜",
        "spill, hello, ⬜⬜🟨🟩⬜",
        "mourn, sorry, ⬜🟩⬜🟩⬜",
        "spill, hello, ⬜⬜🟨🟩⬜",
        "spill, label, 🟨⬜⬜⬜🟩",
        "spill, spell, 🟩🟩⬜🟩🟩",
        "spill, spill, 🟩🟩🟩🟩🟩",
        "react, carry, 🟨🟨🟨⬜⬜"
    )
    fun `정답과 추측을 비교하여 판단한다`(answer: Word, guess: Word, result: String) {
        val judgement = Judgement(answer, guess)

        val actual = judgement.judge()
            .map(Tile::symbol)
            .joinToString("")

        assertEquals(actual, result)
    }
}
