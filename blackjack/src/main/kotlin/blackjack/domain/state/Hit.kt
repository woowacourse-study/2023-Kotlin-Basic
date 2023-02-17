package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Hit(
    cards: Cards,
) : Running(cards) {

    override fun draw(card: Card): State {
        cards.append(card)
        return when {
            cards.isBust() -> Bust(cards)
            else -> Hit(cards)
        }
    }

    override fun stay(): State {
        return Stay(cards)
    }
}
