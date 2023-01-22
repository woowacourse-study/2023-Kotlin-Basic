import domain.AnswerSelector
import domain.Board
import domain.Wordle
import util.readWords
import view.*
import java.time.LocalDate

fun main() {
    printGameStart()
    val allWords = readWords()
    val answer = Wordle.from(AnswerSelector.findTodayAnswer(LocalDate.now(), allWords))
    val board = Board.of(answer, allWords)

    while (board.hasNextRound()) {
        playRound(board)
    }

    printGameResult(win = board.win(), answer = board.answer)
}

private fun playRound(board: Board) {
    runCatching {
        val tryAnswer = inputAnswer()
        val tryResults = board.tryAnswer(Wordle.from(tryAnswer))
        printTryResult(tryResults)
    }.onFailure {
        printInputValidWordle()
        playRound(board)
    }
}
