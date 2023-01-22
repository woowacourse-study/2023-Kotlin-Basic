import domain.PickAnswer
import domain.Wordle
import view.inputPrediction
import view.printStartMessage

fun main() {
    printStartMessage()

    val wordle = Wordle(PickAnswer.Default())
    while (!wordle.isEnd) {
        wordle.proceedRound(inputPrediction())
    }
}
