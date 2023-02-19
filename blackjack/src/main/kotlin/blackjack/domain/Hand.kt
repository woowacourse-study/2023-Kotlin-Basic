package blackjack.domain

class Hand private constructor(
    private val cards: MutableSet<Card>
){
    fun add(card: Card) {
        if (card in cards) {
            throw IllegalArgumentException("같은 카드를 핸드에 추가할 수 없습니다.")
        }

        cards.add(card)
    }

    companion object {
        fun init(card1: Card, card2: Card): Hand {
            return Hand(mutableSetOf(card1, card2))
        }
    }
}
