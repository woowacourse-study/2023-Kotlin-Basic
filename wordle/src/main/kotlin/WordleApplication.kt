import domain.WordleGame
import view.inputWord
import view.printStartMessage

fun main() {
    printStartMessage()
    val game = WordleGame.start()
    do {
        val word = inputWord()
        game.proceed(word)
    } while (game.isInProgress)
}

