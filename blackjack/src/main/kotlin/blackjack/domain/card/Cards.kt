package blackjack.domain.card

class Cards(
    val value: List<Card> = mutableListOf()
) {

    fun totalScore(): Int {
        var totalScore = 0
        value.toList()
            .sortedBy { it.denomination.score }
            .forEach{ totalScore = it.calculateScore(totalScore) }

        return totalScore
    }
}
