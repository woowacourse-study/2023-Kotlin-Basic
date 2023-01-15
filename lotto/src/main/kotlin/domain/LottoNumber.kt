package domain

data class LottoNumber(
    val value: Int
) {
    init {
        require(value in MIN_VALUE..MAX_VALUE) { "로또 번호는 $MIN_VALUE 이상 $MAX_VALUE 이하여야 합니다." }
    }

    override fun toString(): String {
        return "$value"
    }

    companion object {
        const val MIN_VALUE = 1
        const val MAX_VALUE = 45
    }
}
