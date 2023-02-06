import domain.ByDateTimePolicy
import domain.Compared
import domain.Word
import domain.Wordle

fun main() {
    outputIntro()
    val words = readWordsFromFile("wordle/src/main/resources/words.txt").map { Word(it) }
    val wordle = Wordle(words, ByDateTimePolicy())
    val wordleResults = play(wordle)
    if (wordle.hit) outputWinResult(wordle.trial, wordleResults) else outputLoseResult(wordle.answer, wordleResults)
}

private fun play(wordle: Wordle): MutableList<List<Compared>> {
    val guessResult: MutableList<List<Compared>> = mutableListOf()
    while (!wordle.isEnded()) {
        val result = wordle.guess(Word(inputWord()))
        guessResult.add(result)
        outputGuessResults(guessResult)
    }
    return guessResult
}
