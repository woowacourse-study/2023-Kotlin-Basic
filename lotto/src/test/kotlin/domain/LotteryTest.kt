package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class LotteryTest: StringSpec({

    "해당 개수만큼 로또를 구매할 수 있다면 true를 반환한다" {
        val lottery = Lottery(Money(2_000))
        lottery.canBuyLottosOf(2) shouldBe true
    }

    "해당 개수만큼 로또를 구매할 수 없다면 false를 반환한다" {
        val lottery = Lottery(Money(1_900))
        lottery.canBuyLottosOf(2) shouldBe false
    }

    "주어진 금액에서 가능한 많은 자동 로또를 생성한다" {
        val lottery = Lottery(Money(9_999))
        lottery.generateAutoLottos()

        val state = lottery.state()
        state.autoLottos.size shouldBe 9
    }
})

class MoneyTest: StringSpec({

    "로또 금액 이하로 머니를 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> { Money(Lotto.PRICE - 1) }
            .message shouldBe "금액은 ${Lotto.PRICE}원부터 가능합니다"
    }

    "생성한 금액보다 큰 금액을 사용할 수 없다" {
        val money = Money(1_000)
        shouldThrow<IllegalArgumentException> { money.use(1_001) }
            .message shouldBe "금액이 모자라 구매할 수 없습니다"
    }

    "지불 가능한 금액일 경우 true를 반환한다" {
        val money = Money(1_000)
        money.isPayable(1_000) shouldBe true
    }

    "지불 불가능한 금액일 경우 false를 반환한다" {
        val money = Money(1_000)
        money.isPayable(1_001) shouldBe true
    }
})
