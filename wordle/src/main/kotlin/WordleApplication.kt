import domain.WordleGame
import view.inputWord
import view.printAllRoundResults
import view.printRoundCount
import view.printStartMessage

fun main() {
    printStartMessage()
    val game = WordleGame.start()
    do {
        val word = inputWord()
        game.proceed(word)
        printGameResult(game)
    } while (game.isInProgress)
}

fun printGameResult(game: WordleGame) {
    if (game.isEnd) {
        printRoundCount(game.round)
    }
    printAllRoundResults(game.gameResult)
}

