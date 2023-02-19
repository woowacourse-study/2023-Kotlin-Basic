package blackjack.domain.card

private const val BUST_THRESHOLD = 21

class Hand private constructor(
    val cards: MutableSet<Card>
){
    val score: Int
        get() = cards.sumOf { it.score }

    private val bust: Boolean
        get() = score > BUST_THRESHOLD
    // TODO: 버스트 되었지만, ACE가 포함되어 있을 때 ACE를 1점으로 취급하면 버스트가 아닐 경우 ACE를 1점으로 취급해야 한다

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

    companion object {
        fun init(card1: Card, card2: Card): Hand {
            return Hand(mutableSetOf(card1, card2))
        }
    }
}
