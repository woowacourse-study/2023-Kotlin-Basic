package domain

data class LottoNumber(
    val value: Int
) {
    init {
        require(value in MIN_VALUE..MAX_VALUE) { "로또 번호는 1 이상 45 이하여야 합니다." }
    }

    companion object {
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 45
    }
}
