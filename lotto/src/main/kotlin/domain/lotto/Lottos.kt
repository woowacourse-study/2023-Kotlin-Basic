package domain.lotto

import domain.money.Money

class Lottos(
    inputMoney: Money,
    manualLottos: List<Lotto>
) {
    val lottos: List<Lotto>
    val lottoCount: Int
        get() = lottos.size

    init {
        val paidLottoCount = inputMoney.value / Lotto.LOTTO_PRICE
        val autoLottoCount = paidLottoCount - manualLottos.size
        val generatedAutoLottos = generateAutoLottos(autoLottoCount)

        lottos = manualLottos + generatedAutoLottos
    }

    private fun generateAutoLottos(autoLottoCount: Int): List<Lotto> {
        return (1..autoLottoCount).map { Lotto.auto() }
    }
}
