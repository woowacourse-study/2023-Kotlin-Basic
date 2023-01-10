package domain

data class PurchaseMoney(
    private val value: Int
) {
    val purchasableCount: Int
        get() = value / LOTTO_PRICE
    val availableAmount: Int
        get() = value / LOTTO_PRICE * LOTTO_PRICE

    init {
        require(value >= LOTTO_PRICE) { "구입 금액은 로또 한 장의 가격보다 커야 합니다." }
    }

    companion object {
        private const val LOTTO_PRICE = 1_000
    }
}
