package domain

abstract class Participant(startDeck: Started) {

    var deck = startDeck.toDeck()

    fun isFinished() = deck.isFinished()

    open fun draw(card: Card) {
        check(!isFinished()) { "카드를 더 뽑을 수 없습니다" }
        deck = deck.draw(card)
    }
}

class Player(
    val bet: Int,
    cards: Cards
) : Participant(Started(cards)) {

    fun getWinning(dealer: Dealer): Double {
        val ratio = deck.getOdd(dealer.deck).ratio
        return bet.times(ratio)
    }

    fun finish() {
        deck = deck.stay()
    }
}

class Dealer(cards: Cards) : Participant(Started(cards)) {

    private val pot = 0

    init {
        deck = if (deck.score() >= 17) deck.stay() else deck
    }

    override fun draw(card: Card) {
        super.draw(card)
        deck.stay()
    }
}
