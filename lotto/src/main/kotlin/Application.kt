import domain.*
import view.*

fun main() {
    val amount = inputMoneyAmount()
    val lottery = Lottery(Money(amount))

    val manualLottoCount = inputManualCount(lottery)
    addManualLottos(manualLottoCount, lottery)
    lottery.generateAutoLottos()
    outputLotteryStatus(lottery.state())

    val winningLotto = WinningLotto(Lotto { inputWinningLotto() }, Ball(inputBonusBall()))
    val result = lottery.scoredBy(winningLotto)
    outputLotteryResult(result)
}

private fun addManualLottos(manualLottoCount: Int, lottery: Lottery) {
    if (manualLottoCount != 0) {
        val manualLottos = inputLottoNumbers(manualLottoCount).map { Lotto { it } }
        lottery.addManualLottos(manualLottos)
    }
}

private fun inputManualCount(lottery: Lottery): Int {
    val manualLottoCount = inputLottoCount()
    if (!lottery.canBuyLottosOf(manualLottoCount)) {
        throw IllegalArgumentException()
    }
    return manualLottoCount
}
