package domain

abstract class Participant(
    val name: String,
    startDeck: Started
) {

    var deck = startDeck.toDeck()

    fun isFinished() = deck.isFinished()

    open fun draw(card: Card) {
        check(!isFinished()) { "카드를 더 뽑을 수 없습니다" }
        deck = deck.draw(card)
    }

    open fun showCards(): List<Card> = deck.cards()

    abstract fun earning(): Int
}

class Player(
    name: String,
    val bet: Int,
    cards: Cards
) : Participant(name, Started(cards)) {

    var earning = 0

    fun winning(dealer: Dealer): Int {
        val ratio = deck.getOdd(dealer.deck).ratio
        val winning = bet.times(ratio).toInt()
        earning = winning - bet
        return winning
    }

    fun finish() {
        deck = deck.stay()
    }

    override fun earning() = earning
}

class Dealer(
    cards: Cards
) : Participant("딜러", Started(cards)) {

    private var pot = 0
    private var end = false

    init {
        deck = if (deck.score() >= 17) deck.stay() else deck
    }

    override fun draw(card: Card) {
        super.draw(card)
        deck = deck.stay()
    }

    fun deposit(bet: Int) {
        pot += bet
    }

    fun withdraw(winning: Int) {
        pot -= winning
    }

    fun end() { end = true }

    override fun showCards() = if (end) super.showCards() else listOf(deck.cards()[0])

    override fun earning() = pot
}
