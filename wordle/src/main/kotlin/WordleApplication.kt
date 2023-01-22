import domain.JudgementTile
import domain.PickAnswer
import domain.Wordle
import view.*

fun main() {
    printStartMessage()
    val wordle = Wordle(PickAnswer.Default())
    while (!wordle.isEnd) {
        val judgementTiles = retryOnException { wordle.proceedRound(inputPrediction()) }
        printMessageIfEnd(wordle, judgementTiles)
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

private fun printMessageIfEnd(wordle: Wordle, judgementTiles: List<JudgementTile>) {
    if (wordle.isEnd) {
        printTryCount(wordle.round)
        printMessageIfFailed(judgementTiles, wordle)
    }
}

private fun printMessageIfFailed(judgementTiles: List<JudgementTile>, wordle: Wordle) {
    if (!judgementTiles.last().isAllGreen()) {
        printFailMessage(wordle.answer)
    }
}
