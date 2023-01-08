package domain.money

data class Money(
    val value: Int
) {
    companion object {
        private const val MONEY_MIN_UNIT = 1_000
    }

    init {
        require(value % MONEY_MIN_UNIT == 0) {
            IllegalArgumentException("금액은 1,000원 단위여야 합니다.")
        }
    }
}