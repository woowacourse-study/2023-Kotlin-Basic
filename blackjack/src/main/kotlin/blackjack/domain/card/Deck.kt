package blackjack.domain.card

import java.util.LinkedList

interface Deck {
    fun pop(): Card
}

class DefaultDeck: Deck {
    private val deck: LinkedList<Card>

    init {
        val cards = Rank.values().flatMap { rank ->
            Shape.values().map { shape ->
                Card.from(rank, shape)
            }
        }.shuffled()

        deck = LinkedList(cards)
    }

    override fun pop(): Card = deck.pop()
}
