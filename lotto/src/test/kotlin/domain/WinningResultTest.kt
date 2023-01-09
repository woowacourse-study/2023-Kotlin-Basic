package domain

import domain.Rank.*
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe

internal class WinningResultTest : StringSpec({
    "당첨 로또와 구매한 로또로 결과를 판정할 수 있다." {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val purchasedLotteries = listOf(Lotto.from(listOf(1, 2, 3, 4, 5, 6)))

        val winningResult = WinningResult.of(winningLotto, purchasedLotteries)

        assertSoftly {
            winningResult.result shouldContainAll mutableMapOf(
                Pair(FIRST, 1),
                Pair(SECOND, 0),
                Pair(THIRD, 0),
                Pair(FOURTH, 0),
                Pair(FIFTH, 0),
                Pair(FAILED, 0)
            )
            winningResult.totalReward shouldBe 2_000_000_000
        }
    }
})
