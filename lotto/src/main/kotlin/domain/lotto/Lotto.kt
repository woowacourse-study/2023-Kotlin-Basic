package domain.lotto

import domain.lotto.LottoNumber.Companion.MAX_LOTTO_NUMBER
import domain.lotto.LottoNumber.Companion.MIN_LOTTO_NUMBER

class Lotto private constructor(
    val values: List<LottoNumber>
) {
    init {
        require(values.distinct().size == values.size) { "로또 숫자가 중복될 수 없습니다." }
        require(values.distinct().size == LOTTO_SIZE) { "로또 숫자는 6자리여야 합니다." }
    }

    fun matchCount(other: Lotto): Int {
        return values.intersect(other.values.toSet()).size
    }

    fun has(number: LottoNumber): Boolean {
        return number in values
    }

    companion object {
        private const val LOTTO_SIZE = 6
        
        const val LOTTO_PRICE = 1_000

        fun manual(vararg numbers: Int): Lotto {
            val lottoNumbers = numbers.map { LottoNumber(it) }
            return Lotto(lottoNumbers)
        }

        fun auto(): Lotto {
            val lottoNumbers = (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).toList()
                .shuffled()
                .take(LOTTO_SIZE)
                .map { LottoNumber(it) }

            return Lotto(lottoNumbers)
        }
    }
}
