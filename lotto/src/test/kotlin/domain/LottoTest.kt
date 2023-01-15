package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class LottoTest : StringSpec({

    "로또 숫자는 0 이하 46 이상이 될 수 없다" {
        listOf(-1, 0, 46).forAll { invalidNumber ->
            shouldThrow<IllegalArgumentException> { Ball(invalidNumber) }
                .message shouldBe "로또 숫자는 1 이상 45 이하입니다"
        }
    }

    "로또는 중복되지 않는 여섯개의 숫자로 생성해야 한다" {
        listOf(
            listOf(1, 2, 3, 4, 5),
            listOf(1, 2, 3, 4, 5, 6, 7),
            listOf(1, 2, 3, 4, 5, 5)
        ).forAll { numbers ->
            shouldThrow<IllegalArgumentException> { Lotto { numbers } }
                .message shouldBe "로또는 중복되지 않는 6 개 숫자로 구성되어야 합니다"
        }
    }

    "현재 모든 번호의 문자열을 반환한다" {
        val lotto = Lotto { listOf(1, 2, 3, 4, 5, 6) }
        lotto.state() shouldBe "1, 2, 3, 4, 5, 6"
    }

    "우승 로또의 보너스 숫자는 로또 숫자와 중복되지 않아야 한다" {
        val lotto = Lotto { listOf(1, 2, 3, 4, 5, 6) }

        shouldThrow<IllegalArgumentException> { WinningLotto(lotto, Ball(1)) }
            .message shouldBe "보너스 숫자는 로또 번호와 중복될 수 없습니다"
    }
})

class WinningLottoTest : BehaviorSpec({

    Given("우승 로또로 다른 로또를 채점하면") {
        val lotto = Lotto { listOf(1, 2, 3, 4, 5, 6) }
        val winningLotto = WinningLotto(lotto, Ball(7))

        When("숫자가 2개 이하로 일치할 때") {
            val other = Lotto { listOf(1, 2, 33, 34, 35, 36) }

            Then("결과는 FAIL 이다") {
                val prize = winningLotto.score(other)
                prize shouldBe Prize.FAIL
            }
        }

        When("숫자가 3개 일치할 때") {
            val other = Lotto { listOf(1, 2, 3, 32, 33, 34) }

            Then("결과는 5등 이다") {
                val prize = winningLotto.score(other)
                prize shouldBe Prize.FIFTH
            }
        }

        When("숫자가 4개 일치할 때") {
            val other = Lotto { listOf(1, 2, 3, 4, 33, 34) }

            Then("결과는 4등 이다") {
                val prize = winningLotto.score(other)
                prize shouldBe Prize.FORTH
            }
        }

        When("숫자가 5개 일치하고, 보너스는 일치하지 않을 때") {
            val other = Lotto { listOf(1, 2, 3, 4, 5, 34) }

            Then("결과는 3등 이다") {
                val prize = winningLotto.score(other)
                prize shouldBe Prize.THIRD
            }
        }

        When("숫자가 5개 일치하고, 보너스도 일치할 때") {
            val other = Lotto { listOf(1, 2, 3, 4, 5, 7) }

            Then("결과는 2등 이다") {
                val prize = winningLotto.score(other)
                prize shouldBe Prize.SECOND
            }
        }

        When("숫자가 6개 일치하면") {
            val other = Lotto { listOf(1, 2, 3, 4, 5, 6) }

            Then("결과는 1등 이다") {
                val prize = winningLotto.score(other)
                prize shouldBe Prize.FIRST
            }
        }
    }
})
