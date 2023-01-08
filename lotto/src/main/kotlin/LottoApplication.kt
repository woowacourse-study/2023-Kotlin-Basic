import domain.PurchaseMoney
import view.printErrorMessage
import view.readPurchaseMoney

fun main() {
    val purchaseMoney = inputPurchaseMoney()
}

private fun inputPurchaseMoney(): PurchaseMoney {
    return try {
        PurchaseMoney(readPurchaseMoney())
    } catch (e: IllegalArgumentException) {
        printErrorMessage(e.message)
        inputPurchaseMoney()
    }
}
