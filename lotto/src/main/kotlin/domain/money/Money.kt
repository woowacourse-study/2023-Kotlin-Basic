package domain.money

data class Money(
    val value: Int
) {
    init {
        require(value >= 0) { "금액은 음수일 수 없습니다." }
        require(isMultipleOfMinUnit()) { "금액은 1,000원 단위여야 합니다." }
    }

    private fun isMultipleOfMinUnit(): Boolean {
        return value % MONEY_MIN_UNIT == 0
    }

    companion object {
        private const val MONEY_MIN_UNIT = 1_000
    }
}
