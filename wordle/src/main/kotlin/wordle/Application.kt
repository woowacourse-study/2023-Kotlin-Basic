package wordle

import wordle.domain.Game
import wordle.infra.FileWordsReader
import wordle.view.ConsoleIOProcessor
import java.util.*

fun main() {
    val game = Game(wordsReader = FileWordsReader(), ioProcessor = ConsoleIOProcessor(Scanner(System.`in`)))
    game.play()
}
