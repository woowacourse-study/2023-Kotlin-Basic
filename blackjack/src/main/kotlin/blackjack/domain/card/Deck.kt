package blackjack.domain.card

private const val TOP_OF_DECK_INDEX = 0

interface Deck {
    fun pop(): Card
}

class DefaultDeck : Deck {
    private val deck: MutableList<Card>

    init {
        val cards = Rank.values().flatMap { rank ->
            Shape.values().map { shape ->
                Card.from(rank, shape)
            }
        }.shuffled().toMutableList()

        deck = cards
    }

    override fun pop(): Card = deck.removeAt(TOP_OF_DECK_INDEX)
}
