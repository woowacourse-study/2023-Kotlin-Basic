import domain.PickAnswer
import domain.Wordle
import view.inputPrediction
import view.printStartMessage
import view.printTiles

fun main() {
    printStartMessage()

    val wordle = Wordle(PickAnswer.Default())
    while (!wordle.isEnd) {
        printTiles(wordle.proceedRound(inputPrediction()))
    }
}
