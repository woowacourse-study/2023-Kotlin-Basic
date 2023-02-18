package domain

abstract class Participant(
    cards: Cards
) {
    var deck = cards.toDeck()

    fun isFinished() = deck.isFinished()

    open fun draw(card: Card) {
        check(!deck.isFinished()) { "카드를 더 뽑을 수 없습니다" }
        deck = deck.draw(card)
    }
}

class Player(
    val bet: Int,
    cards: Cards
) : Participant(cards) {

}

class Dealer(
    cards: Cards
) : Participant(cards) {
    private val pot = 0

    init {
        val initialDeck = cards.toDeck()
        deck = if (initialDeck.score() >= 17) initialDeck.stay() else initialDeck
    }

    override fun draw(card: Card) {
        super.draw(card)
        deck.stay()
    }
}
