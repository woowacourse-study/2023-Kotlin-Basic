import domain.lotto.Lottos
import domain.winning.Statistics
import view.*

fun main() {
    val inputMoney = retryOnException { inputMoney() }
    val manualLottoCount = retryOnException { inputManualLottoCount() }
    val manualLottos = retryOnException { inputManualLottos(manualLottoCount) }

    val lottos = Lottos(inputMoney, manualLottos)
    printLottoBundle(lottos, manualLottoCount)

    val winningLotto = retryOnException { inputWinningLotto() }

    val statistics = Statistics(lottos, winningLotto)
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
