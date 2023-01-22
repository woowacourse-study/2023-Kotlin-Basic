import domain.PickAnswer
import domain.Wordle
import view.*

fun main() {
    printStartMessage()

    val wordle = Wordle(PickAnswer.Default())
    while (!wordle.isEnd) {
        val judgementTiles = retryOnException { wordle.proceedRound(inputPrediction()) }
        if (wordle.isEnd) {
            printTryCount(wordle.round)
            if (!judgementTiles.last().isAllGreen()) {
                printFailMessage(wordle.answer)
            }
        }
        printTiles(judgementTiles)
    }
}

private fun <T> retryOnException(function: () -> T): T {
    return try {
        function()
    } catch (e: Exception) {
        println(e.localizedMessage)
        retryOnException(function)
    }
}
