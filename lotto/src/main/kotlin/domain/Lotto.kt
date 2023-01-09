package domain

class Lotto private constructor(
    val numbers: List<LottoNumber>
) {
    init {
        require(numbers.size == NUMBERS_SIZE) { "로또 번호는 6개여야 합니다." }
        require(numbers.size == numbers.distinct().size) { "로또 번호에 중복이 있을 수 없습니다." }
    }

    fun contains(number: LottoNumber): Boolean = numbers.contains(number)

    fun countMatches(target: Lotto): Int = numbers.count { target.contains(it) }

    companion object {
        private const val NUMBERS_SIZE = 6

        fun from(numbers: List<Int>): Lotto = Lotto(numbers.map { LottoNumber(it) })
    }
}
