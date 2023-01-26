package wordle

import wordle.domain.Game
import wordle.infra.FileWordsReader
import wordle.view.ConsoleIOProcessor

fun main() {
    val game = Game(wordsReader = FileWordsReader(), ioProcessor = ConsoleIOProcessor())
    game.play()
}
