import domain.*
import view.*

fun main() {
    val amount = inputMoneyAmount()
    val lottos = Lottos(Money(amount))

    val manualLottoCount = inputManualCount(lottos)
    addManualLottos(manualLottoCount, lottos)
    lottos.generateAutoLottos()
    outputLotteryStatus(lottos.state())

    val winningLotto = WinningLotto(Lotto { inputWinningLotto() }, Ball(inputBonusBall()))
    val result = lottos.scoredBy(winningLotto)
    outputLotteryResult(result)
}

private fun addManualLottos(manualLottoCount: Int, lottos: Lottos) {
    if (manualLottoCount != 0) {
        val manualLottos = inputLottoNumbers(manualLottoCount).map { Lotto { it } }
        lottos.addManualLottos(manualLottos)
    }
}

private fun inputManualCount(lottos: Lottos): Int {
    val manualLottoCount = inputLottoCount()
    if (!lottos.canBuyLottosOf(manualLottoCount)) {
        throw IllegalArgumentException()
    }
    return manualLottoCount
}
