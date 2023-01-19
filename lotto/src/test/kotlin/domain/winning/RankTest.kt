package domain.winning

import domain.lotto.Lotto
import domain.lotto.LottoNumber
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class RankTest : BehaviorSpec({
    Given("WinningLotto가 생성되었을 때") {
        val winningLotto = WinningLotto(
            Lotto.manual(1, 2, 3, 4, 5, 6),
            LottoNumber(7)
        )

        When("보너스 번호를 제외하고, 6개의 번호가 일치하면") {
            val lotto = Lotto.manual(1, 2, 3, 4, 5, 6)
            val actual = Rank.determine(lotto, winningLotto)

            Then("Rank.FIRST를 반환한다.") {
                actual shouldBe Rank.FIRST
            }
        }

        When("보너스 번호를 포함하여, 6개의 번호가 일치하면") {
            val lotto = Lotto.manual(1, 2, 3, 4, 5, 7)
            val actual = Rank.determine(lotto, winningLotto)

            Then("Rank.SECOND를 반환한다.") {
                actual shouldBe Rank.SECOND
            }
        }

        When("보너스 번호를 포함하여, 5개의 번호가 일치하면") {
            val lotto = Lotto.manual(1, 2, 3, 4, 5, 45)
            val actual = Rank.determine(lotto, winningLotto)

            Then("Rank.THIRD를 반환한다.") {
                actual shouldBe Rank.THIRD
            }
        }

        When("보너스 번호를 포함하여, 4개의 번호가 일치하면") {
            val lotto = Lotto.manual(1, 2, 3, 4, 44, 45)
            val actual = Rank.determine(lotto, winningLotto)

            Then("Rank.FOURTH를 반환한다.") {
                actual shouldBe Rank.FOURTH
            }
        }

        When("보너스 번호를 포함하여, 3개의 번호가 일치하면") {
            val lotto = Lotto.manual(1, 2, 3, 43, 44, 45)
            val actual = Rank.determine(lotto, winningLotto)

            Then("Rank.FIFTH를 반환한다.") {
                actual shouldBe Rank.FIFTH
            }
        }
    }
})
