package lotto.domain.Lotto

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class LottoTest {

    @ParameterizedTest
    @MethodSource("generateConditions")
    fun `결과를 판정한다`(
        description: String,
        lottoNumbers: List<Int>,
        winningLotto: Lotto,
        bonusNumber: Number,
        rank: Int,
    ) {
        val lotto = Lotto.fromInts(lottoNumbers)

        val judgeWinning = lotto.judgeWinning(winningLotto, bonusNumber)

        judgeWinning.calculateRank() shouldBe rank
    }

    companion object {

        val winningLotto = Lotto.fromInts(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = Number(7)

        @JvmStatic
        fun generateConditions(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("다 맞추고 1등인 경우", listOf(1, 2, 3, 4, 5, 6), winningLotto, bonusNumber, 1),
                Arguments.of("5개 맞추고 보너스 맞춰서 2등인 경우", listOf(1, 2, 3, 4, 5, 7), winningLotto, bonusNumber, 2),
                Arguments.of("5개 맞추고 보너스 못맞춰서 3등인 경우", listOf(1, 2, 3, 4, 5, 8), winningLotto, bonusNumber, 3),
                Arguments.of("4개 맞추고 4등인 경우", listOf(1, 2, 3, 4, 9, 10), winningLotto, bonusNumber, 4),
                Arguments.of("3개 이하 맞추고 5등인 경우", listOf(1, 2, 3, 9, 10, 11), winningLotto, bonusNumber, 5),
            )
        }
    }
}
