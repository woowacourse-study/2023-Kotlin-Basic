package lotto.domain.Lotto

object LottoFactory {

    private const val MONEY_UNIT = 1000L
    private const val LOTTO_NUMBERS_COUNT = 6

    fun purchaseLottos(money: Long): List<Lotto> {
        val buyCount = money / MONEY_UNIT
        return createRandomLottos(buyCount)
    }

    private fun createRandomLottos(buyCount: Long): List<Lotto> {
        val lottos = mutableListOf<Lotto>()
        for (i in 1..buyCount) {
            lottos.add(Lotto.ofNumbers(Numbers.getShuffledNumbers(LOTTO_NUMBERS_COUNT)))
        }
        return lottos
    }
}
