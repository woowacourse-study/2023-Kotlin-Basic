package blackjack.domain

import java.util.LinkedList

class Deck {
    private val deck: LinkedList<Card>

    init {
        val cards = Rank.values().flatMap { rank ->
            Shape.values().map { shape ->
                Card.from(rank, shape)
            }
        }.shuffled()

        deck = LinkedList(cards)
    }

    fun pop(): Card = deck.pop()
}
