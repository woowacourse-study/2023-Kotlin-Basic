import domain.*
import view.*

fun main() {
    val purchaseMoney = inputPurchaseMoney()
    val totalPurchaseCount = PurchaseCount(purchaseMoney)

    val manualCount = inputManualCount(totalPurchaseCount)
    val lotteries = mutableListOf<Lotto>()
    lotteries.addAll(issueManualLotteries(manualCount))

    val autoCount = totalPurchaseCount - manualCount
    lotteries.addAll(LottoMachine.issueAutomatically(autoCount))
    printLotteries(lotteries, manualCount, autoCount)

    val winningLotto = WinningLotto(readWinningNumbers(), readBonusBall())
    val winningResult = WinningResult.of(winningLotto, lotteries)
    printWinningResult(winningResult)
}

private fun inputPurchaseMoney(): PurchaseMoney {
    return try {
        PurchaseMoney(readPurchaseMoney())
    } catch (e: IllegalArgumentException) {
        printErrorMessage(e.message)
        inputPurchaseMoney()
    }
}

private fun inputManualCount(totalPurchaseCount: PurchaseCount): PurchaseCount {
    return try {
        val manualCount = readManualCount()
        require(manualCount <= totalPurchaseCount.value) {
            "${totalPurchaseCount}장 보다 많이 구매할 수 없습니다."
        }
        PurchaseCount(manualCount)
    } catch (e: IllegalArgumentException) {
        printErrorMessage(e.message)
        inputManualCount(totalPurchaseCount)
    }
}

private fun issueManualLotteries(count: PurchaseCount): List<Lotto> {
    fun issueManualLotto(): Lotto {
        return try {
            Lotto.from(readManualLottoNumbers())
        } catch (e: IllegalArgumentException) {
            printErrorMessage(e.message)
            issueManualLotto()
        }
    }

    return (1..count.value).map { issueManualLotto() }
}
