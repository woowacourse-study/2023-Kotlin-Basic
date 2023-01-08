package domain.winning

import domain.lotto.Lotto
import domain.lotto.LottoBundle
import domain.lotto.LottoNumber
import domain.money.Money
import domain.winning.Rank.*
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class StatisticsTest : BehaviorSpec({
    Given("구매한 로또 목록과 당첨 번호가 제공되었을 때") {
        val lottos = listOf(
            Lotto.manual(1, 2, 3, 4, 5, 6),
            Lotto.manual(1, 2, 3, 4, 5, 7),
            Lotto.manual(1, 2, 3, 4, 5, 45),
            Lotto.manual(1, 2, 3, 4, 44, 45),
            Lotto.manual(1, 2, 3, 43, 44, 45),
            Lotto.manual(1, 2, 42, 43, 44, 45),
            Lotto.manual(1, 41, 42, 43, 44, 45),
            Lotto.manual(2, 13, 22, 32, 38, 45),
            Lotto.manual(23, 25, 33, 36, 39, 41),
            Lotto.manual(1, 3, 5, 14, 22, 45),
            Lotto.manual(5, 9, 38, 41, 43, 44),
            Lotto.manual(2, 8, 9, 18, 19, 21),
            Lotto.manual(13, 14, 18, 21, 23, 35),
            Lotto.manual(17, 21, 29, 37, 42, 45),
            Lotto.manual(3, 8, 27, 30, 35, 44)
        )
        val lottoBundle = LottoBundle(Money(15_000), lottos)

        val winningLotto = WinningLotto(
            Lotto.manual(1, 2, 3, 4, 5, 6),
            LottoNumber(7)
        )

        When("당첨 통계를 계산하면") {
            val statistics = Statistics(lottoBundle, winningLotto)

            Then("1등은 1개이다.") {
                statistics.ranks.get(FIRST) shouldBe 1
            }

            Then("2등은 1개이다.") {
                statistics.ranks.get(SECOND) shouldBe 1
            }

            Then("3등은 1개이다.") {
                statistics.ranks.get(THIRD) shouldBe 1
            }

            Then("4등은 1개이다.") {
                statistics.ranks.get(FOURTH) shouldBe 1
            }

            Then("5등은 2개이다.") {
                statistics.ranks.get(FIFTH) shouldBe 2
            }

            Then("수익률은 135437.33333333334 이다.") {
                statistics.profitRate shouldBe 135437.33333333334
            }
        }
    }

})
