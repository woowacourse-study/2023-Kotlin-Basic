package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PurchaseCountTest : StringSpec({
    "구입 장 수가 음수면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { PurchaseCount(-1) }
            .message shouldBe "구입 장 수는 0장 이상이어야 합니다."
    }

    "구입 금액 천 원당 1개의 값을 가진 구입 장 수를 생성할 수 있다." {
        val purchaseMoney = PurchaseMoney(2_000)

        PurchaseCount(purchaseMoney) shouldBe PurchaseCount(2)
    }

    "구입 장 수 끼리 뺄셈을 할 수 있다." {
        val purchaseCount = PurchaseCount(2)
        val other = PurchaseCount(1)

        purchaseCount - other shouldBe PurchaseCount(1)
    }
})
