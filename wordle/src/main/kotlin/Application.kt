import domain.AnswerSelector
import domain.Board
import domain.Wordle
import util.readWords
import view.inputAnswer
import view.printGameResult
import view.printGameStart
import view.printTryResult
import java.time.LocalDate

fun main() {
    printGameStart()
    val allWords = readWords()
    val answer = Wordle.from(AnswerSelector.findTodayAnswer(LocalDate.now(), allWords))
    val board = Board.of(answer, allWords)

    while (board.hasNextRound()) {
        val tryAnswer = inputAnswer()
        val tryResults = board.tryAnswer(Wordle.from(tryAnswer))
        printTryResult(tryResults)
    }

    printGameResult(win = board.win(), answer = board.answer)
}
