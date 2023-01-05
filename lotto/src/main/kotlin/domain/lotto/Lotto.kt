package domain.lotto

class Lotto private constructor(
    private val values: List<LottoNumber>
) {
    companion object Factory {
        fun manual(vararg numbers: Int): Lotto {
            require(numbers.distinct().size == 6) {
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
}
