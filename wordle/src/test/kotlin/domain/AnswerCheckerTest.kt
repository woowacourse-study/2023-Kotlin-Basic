package domain

import domain.Tile.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class AnswerCheckerTest {

    @DisplayName("정답: river, 입력받은 답안: rower")
    @Test
    fun checkGreenTiles() {
        val answerChecker: AnswerChecker = AnswerChecker(Answer("river"))
        val roundResult: RoundResult = answerChecker.check(Word("rower"))

        assertThat(roundResult).isEqualTo(RoundResult(mutableListOf(GREEN, GREY, GREY, GREEN, GREEN)))
    }

    @DisplayName("정답: still, 입력받은 답안: sully")
    @Test
    fun checkYellowTiles() {
        val answerChecker: AnswerChecker = AnswerChecker(Answer("still"))
        val roundResult: RoundResult = answerChecker.check(Word("sully"))

        assertThat(roundResult).isEqualTo(RoundResult(mutableListOf(GREEN, GREY, GREY, GREEN, YELLOW)))
    }
}
