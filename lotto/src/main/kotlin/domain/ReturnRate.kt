package domain

class ReturnRate private constructor(
    val value: Double
) {
    companion object {
        fun calculate(totalReward: Int, purchaseMoney: PurchaseMoney): ReturnRate = ReturnRate(
            totalReward.toDouble() / purchaseMoney.availableAmount
        )
    }
}
