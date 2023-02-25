package blackjack.domain.testdouble

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Rank
import blackjack.domain.card.Shape
import java.util.*

class FakeDeck: Deck {
    var deck: LinkedList<Card> = LinkedList(
        Rank.values().flatMap { rank ->
            Shape.values().map { shape ->
                Card.from(rank, shape)
            }
        }
    )

    override fun pop(): Card = deck.pop()
}
