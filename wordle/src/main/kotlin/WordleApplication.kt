import domain.WordleGame
import view.*

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
        printAnswer(game.answer)
    }
    printAllRoundResults(game.gameResult)
}

