package domain.lotto

data class LottoNumber(
    val value: Int
) {
    companion object {
        const val MIN_LOTTO_NUMBER = 1
        const val MAX_LOTTO_NUMBER = 45
    }

    init {
        require(value in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) {
            "로또 번호는 $MIN_LOTTO_NUMBER ~ $MAX_LOTTO_NUMBER 사이여야 합니다."
        }
    }
}
