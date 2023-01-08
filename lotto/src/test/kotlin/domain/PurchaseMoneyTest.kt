package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PurchaseMoneyTest : StringSpec({
    "구입 금액이 로또의 가격보다 적으면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { PurchaseMoney(999) }
            .message shouldBe "구입 금액은 로또 한 장의 가격보다 커야 합니다."
    }

    "천 원 당 한 장의 로또를 구매할 수 있다." {
        val money = PurchaseMoney(1_001)

        money.purchasableCount shouldBe 1
    }
})
