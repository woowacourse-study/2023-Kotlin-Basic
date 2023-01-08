package domain.lotto

class Lotto(
    private var values: List<LottoNumber>
) {
    companion object {
        private const val LOTTO_SIZE = 6
        const val LOTTO_PRICE = 1_000

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

    override fun toString(): String {
        return values.toString()
    }
}
