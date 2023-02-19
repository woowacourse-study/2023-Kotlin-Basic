package blackjack.domain.card

private const val BLACKJACK_SCORE = 21
private const val READY_SIZE = 2

class Cards(
    val value: MutableList<Card> = mutableListOf(),
) {

    fun isBlackjack(): Boolean {
        return isReady() && hasAce() && totalScore() == BLACKJACK_SCORE
    }

    fun isReady(): Boolean {
        return value.size == READY_SIZE
    }

    private fun hasAce(): Boolean {
        return value.any(Card::isAce)
    }

    fun isBust(): Boolean {
        return totalScore() > BLACKJACK_SCORE
    }

    fun totalScore(): Int {
        var totalScore = 0
        value.toList()
            .sortedBy { it.denomination.score }
            .forEach { totalScore = it.calculateScore(totalScore) }

        return totalScore
    }

    fun append(card: Card) {
        value.add(card)
    }
}
