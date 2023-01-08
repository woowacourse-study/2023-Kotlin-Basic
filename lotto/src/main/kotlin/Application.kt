import domain.lotto.LottoBundle
import domain.winning.Statistics
import view.*

fun main() {
    val inputMoney = retryOnException { inputMoney() }
    val manualLottoCount = retryOnException { inputManualLottoCount() }
    val manualLottos = retryOnException { inputManualLottos(manualLottoCount) }

    val lottoBundle = LottoBundle(inputMoney, manualLottos)
    printLottoBundle(lottoBundle, manualLottoCount)

    val winningLotto = retryOnException { inputWinningLotto() }

    val statistics = Statistics(lottoBundle, winningLotto)
    printStatistics(statistics)
}

fun <T> retryOnException(function: () -> T): T {
    return try {
        function()
    } catch (e: Exception) {
        println(e.localizedMessage)
        retryOnException(function)
    }
}
