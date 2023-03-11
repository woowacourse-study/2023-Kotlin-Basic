package blackjack.domain.card

private const val BUST_THRESHOLD = 21
private const val ACE_SCORE_ADJUSTMENT_AMOUNT = 10

class Hand private constructor(
    val cards: MutableSet<Card>
) {
    val score: Int
        get() = calculateScore()

    val bust: Boolean
        get() = score > BUST_THRESHOLD

    /**
     * add 이후 더 카드를 뽑을 수 있는 상태인지 (!bust) 반환한다.
     */
    fun add(card: Card): Boolean {
        if (card in cards) {
            throw IllegalArgumentException("같은 카드를 핸드에 추가할 수 없습니다.")
        }

        cards.add(card)
        return !bust
    }

    private fun calculateScore(): Int {
        var sumOfScore = cards.sumOf { it.score }
        if (cards.hasAce()) {
            sumOfScore -= ACE_SCORE_ADJUSTMENT_AMOUNT
        }
        return sumOfScore
    }

    companion object {
        fun init(card1: Card, card2: Card): Hand {
            return Hand(mutableSetOf(card1, card2))
        }
    }
}
