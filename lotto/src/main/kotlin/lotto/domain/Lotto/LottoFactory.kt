package lotto.domain.Lotto

import java.lang.IllegalArgumentException

object LottoFactory {

    private const val MONEY_UNIT = 1000L
    private const val LOTTO_NUMBERS_COUNT = 6

    fun purchaseLottos(money: Long): List<Lotto> {
        validateMoney(money)
        val buyCount = money / MONEY_UNIT
        return createRandomLottos(buyCount)
    }

    private fun validateMoney(money: Long) {
        if (money < 0) {
            throw IllegalArgumentException("음수는 들어올 수 없습니다.")
        }
    }

    private fun createRandomLottos(buyCount: Long): List<Lotto> {
        val lottos = mutableListOf<Lotto>()
        for (i in 1..buyCount) {
            lottos.add(Lotto.fromInts(Numbers.getShuffledNumbers(LOTTO_NUMBERS_COUNT)))
        }
        return lottos
    }
}
