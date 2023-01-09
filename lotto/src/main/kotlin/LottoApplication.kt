import domain.Lotto
import domain.LottoMachine
import domain.PurchaseMoney
import view.*

fun main() {
    val purchaseMoney = inputPurchaseMoney()

    val manualCount = inputManualCount(purchaseMoney)
    val lotteries = mutableListOf<Lotto>()
    lotteries.addAll(issueManualLotteries(manualCount))

    val autoCount = purchaseMoney.purchasableCount - manualCount
    lotteries.addAll(LottoMachine.issueAutomatically(autoCount))
    printLotteries(lotteries)
}

private fun inputPurchaseMoney(): PurchaseMoney {
    return try {
        PurchaseMoney(readPurchaseMoney())
    } catch (e: IllegalArgumentException) {
        printErrorMessage(e.message)
        inputPurchaseMoney()
    }
}

private fun inputManualCount(purchaseMoney: PurchaseMoney): Int {
    return try {
        val manualCount = readManualCount()
        require(manualCount <= purchaseMoney.purchasableCount) {
            "${purchaseMoney.purchasableCount}장 보다 많이 구매할 수 없습니다."
        }
        return manualCount
    } catch (e: IllegalArgumentException) {
        printErrorMessage(e.message)
        inputManualCount(purchaseMoney)
    }
}

private fun issueManualLotteries(count: Int): List<Lotto> {
    fun issueManualLotto(): Lotto {
        return try {
            Lotto.from(readManualLottoNumbers())
        } catch (e: IllegalArgumentException) {
            printErrorMessage(e.message)
            issueManualLotto()
        }
    }

    val lotteries = mutableListOf<Lotto>()
    for (i in 1..count) {
        lotteries.add(issueManualLotto())
    }
    return lotteries
}
