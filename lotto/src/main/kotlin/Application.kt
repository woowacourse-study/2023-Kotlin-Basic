import domain.lotto.LottoBundle
import domain.winning.Statistics
import view.*

fun main() {
    val inputMoney = inputMoney()
    val manualLottoCount = inputManualLottoCount()
    val manualLottos = inputManualLottos(manualLottoCount)

    val lottoBundle = LottoBundle(inputMoney, manualLottos)
    printLottoBundle(lottoBundle, manualLottoCount)

    val winningLotto = inputWinningLotto()

    val statistics = Statistics(lottoBundle, winningLotto)
    printStatistics(statistics)
}
