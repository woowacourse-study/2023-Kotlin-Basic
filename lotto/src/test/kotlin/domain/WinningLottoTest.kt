package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.match

internal class WinningLottoTest : StringSpec({
    "당첨 번호와 보너스 볼이 중복되면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { WinningLotto(listOf(1, 2, 3, 4, 5, 6), 6) }
            .message should match("당첨 번호와 보너스 볼은 중복될 수 없습니다.")
    }

    "2등과 3등을 제외하면 구매한 로또가 포함하고 있는 당첨 번호 개수에 따라 등수가 정해진다." {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        listOf(
            row(Lotto.from(listOf(1, 2, 3, 4, 5, 6)), Rank.FIRST),
            row(Lotto.from(listOf(1, 2, 3, 4, 8, 9)), Rank.FOURTH),
            row(Lotto.from(listOf(1, 2, 3, 8, 9, 10)), Rank.FIFTH),
            row(Lotto.from(listOf(1, 2, 8, 9, 10, 11)), Rank.FAILED)
        ).forEach { (lotto, rank) -> winningLotto.judgeRank(lotto) shouldBe rank }
    }

    "구매한 로또가 당첨 번호를 5개 포함하고 있고 보너스 볼을 포함하고 있으면 2등이다." {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        winningLotto.judgeRank(Lotto.from(listOf(1, 2, 3, 4, 5, 7))) shouldBe Rank.SECOND
    }

    "구매한 로또가 당첨 번호를 5개 포함하고 있고 보너스 볼을 포함하지 않으면 3등이다." {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        winningLotto.judgeRank(Lotto.from(listOf(1, 2, 3, 4, 5, 8))) shouldBe Rank.THIRD
    }
})
