package domain

class GameResult(private val roundResults: MutableList<RoundResult> = mutableListOf()) : Iterable<RoundResult> {

    fun appendResult(roundResult: RoundResult) {
        roundResults.add(roundResult)
    }

    override fun iterator(): Iterator<RoundResult> = roundResults.iterator()
}
