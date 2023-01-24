import domain.Game
import domain.WordList
import view.*
import java.io.File
import java.time.LocalDateTime

private const val WORDS_TXT_PATH = "src/main/resources/words.txt"

fun main() {
    val wordList = WordList(readWordsTxt().trim())
    val game = Game(wordList)

    printTitleMessage()

    for (round in 1..6) {
        printSubmitWordMessage()
        if (submitSingleWord(game, round)) break
    }

    printFailMessage()
}

private fun readWordsTxt(): String {
    val file = File(WORDS_TXT_PATH)
    return file.readText()
}

private fun submitSingleWord(game: Game, round: Int): Boolean {
    val result = game.submitWord(inputText(), LocalDateTime.now())

    printLetterCompareResults(game.letterCompareResultsList.toList())

    if (result.isCorrect) {
        printSuccessMessage(round)
        return true
    }

    return false
}
