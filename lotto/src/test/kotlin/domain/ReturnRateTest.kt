package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ReturnRateTest : StringSpec({
    "수익률을 계산할 수 있다." {
        val totalReward = 1_000
        val purchaseMoney = PurchaseMoney(2_000)

        ReturnRate.calculate(totalReward, purchaseMoney).value shouldBe 0.5
    }
})
