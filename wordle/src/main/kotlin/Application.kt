import domain.Compared
import domain.Word
import domain.Wordle
import java.time.LocalDateTime

fun main() {
    outputIntro()
    val answer = readAnswer()
    val wordle = Wordle(Word(answer))
    val wordleResults = play(wordle)
    outputWordleResult(wordle.trial, wordleResults)
}

private fun readAnswer() = getWordFromFile(
    "wordle/src/main/resources/words.txt",
    LocalDateTime.of(2021, 6, 19, 0, 0),
    LocalDateTime.now()
)

private fun play(wordle: Wordle): MutableList<List<Compared>> {
    val guessResult: MutableList<List<Compared>> = mutableListOf()
    while (!wordle.isEnded()) {
        val result = wordle.guess(Word(inputWord()))
        guessResult.add(result)
        outputGuessResults(guessResult)
    }
    return guessResult
}
