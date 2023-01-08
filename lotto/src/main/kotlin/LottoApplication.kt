import domain.PurchaseMoney
import view.printErrorMessage
import view.readManualCount
import view.readPurchaseMoney

fun main() {
    val purchaseMoney = inputPurchaseMoney()
    val manualCount = inputManualCount(purchaseMoney)
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
