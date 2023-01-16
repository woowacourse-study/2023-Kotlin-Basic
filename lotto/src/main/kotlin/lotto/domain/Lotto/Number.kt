package lotto.domain.Lotto

data class Number(
    val value: Int
) {

    companion object {
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 45
    }

    init {
        validateRange(value)
    }

    private fun validateRange(value: Int) {
        if (value !in MIN_VALUE..MAX_VALUE) {
            throw IllegalArgumentException("value 는 1~45여야 합니다")
        }
    }

    override fun toString(): String {
        return value.toString()
    }
}
