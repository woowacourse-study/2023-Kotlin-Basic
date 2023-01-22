package domain

data class PurchaseMoney(
    val value: Int
) {
    init {
        require(value >= LOTTO_PRICE) { "구입 금액은 로또 한 장의 가격보다 커야 합니다." }
        require(value % LOTTO_PRICE == 0) { "구입 금액은 천 원 단위어야 합니다." }
    }

    companion object {
        const val LOTTO_PRICE = 1_000
    }
}
