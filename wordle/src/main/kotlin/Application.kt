import domain.Compared
import domain.FromFileByDatePolicy
import domain.Word
import domain.Wordle

fun main() {
    outputIntro()
    val wordle = Wordle(FromFileByDatePolicy())
    val wordleResults = play(wordle)
    outputWordleResult(wordle.trial, wordleResults)
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
