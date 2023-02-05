package domain

class Board private constructor(
    val answer: Wordle,
    val possibleWordles: List<Wordle>
) {

    companion object {
        private const val MAX_TRY_COUNT = 6

        fun of(answer: Wordle, possibleWords: List<String>): Board =
            Board(
                possibleWordles = possibleWords.map { Wordle.from(it) },
                answer = answer
            )
    }

    private val wordles = mutableListOf<Wordle>()

    fun tryAnswer(tryAnswer: Wordle): List<String> {
        validateInPossibleWordles(tryAnswer)

        wordles.add(tryAnswer)
        return wordles.map { answer.markAnswer(tryAnswer) }
    }

    fun canPlay(): Boolean =
        wordles.size < MAX_TRY_COUNT

    fun win(): Boolean =
        wordles.contains(answer)

    fun hasNextRound(): Boolean =
        canPlay() && !win()

    private fun validateInPossibleWordles(tryAnswer: Wordle) {
        if (tryAnswer !in possibleWordles) {
            throw IllegalArgumentException("가능한 단어 목록 중에 있어야 합니다")
        }
    }
}
