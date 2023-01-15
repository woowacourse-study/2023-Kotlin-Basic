package domain

import domain.PurchaseMoney.Companion.LOTTO_PRICE

data class PurchaseCount(
    val value: Int
) {
    constructor(purchaseMoney: PurchaseMoney) : this(purchaseMoney.value / LOTTO_PRICE)

    init {
        require(value >= 0) { "구입 장 수는 0장 이상이어야 합니다." }
    }

    operator fun minus(other: PurchaseCount) = PurchaseCount(value - other.value)

    override fun toString(): String = "$value"
}
