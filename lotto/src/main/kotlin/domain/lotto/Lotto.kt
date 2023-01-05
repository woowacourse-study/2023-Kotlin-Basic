package domain.lotto

class Lotto private constructor(
    private val values: List<LottoNumber>
) {
    companion object {
        private const val LOTTO_SIZE = 6

        fun manual(vararg numbers: Int): Lotto {
            require(numbers.distinct().size == LOTTO_SIZE) {
                IllegalArgumentException("로또 숫자는 6자리여야 합니다.")
            }

            val lottoNumbers: List<LottoNumber> = numbers.map { LottoNumber(it) }
            return Lotto(lottoNumbers)
        }

        fun auto(): Lotto {
            val lottoNumbers: List<LottoNumber> = (1..45).toList()
                .shuffled()
                .take(6)
                .map { LottoNumber(it) }

            return Lotto(lottoNumbers)
        }
    }

    fun match(other: Lotto): Int {
        val distinctRemovedCount = (values + other.values).distinct().size
        return (LOTTO_SIZE * 2) - distinctRemovedCount
    }

    fun has(number: LottoNumber): Boolean {
        return number in values
    }
}
