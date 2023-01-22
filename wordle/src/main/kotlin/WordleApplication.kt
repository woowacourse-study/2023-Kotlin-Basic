import domain.PickAnswer
import domain.Wordle
import view.inputPrediction
import view.printStartMessage
import view.printTiles
import view.printTryCount

fun main() {
    printStartMessage()

    val wordle = Wordle(PickAnswer.Default())
    while (!wordle.isEnd) {
        val judgementTiles = wordle.proceedRound(inputPrediction())
        if (wordle.isEnd) {
            printTryCount(wordle.round)
        }
        printTiles(judgementTiles)
    }
}
