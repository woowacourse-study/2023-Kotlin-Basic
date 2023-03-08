package blackjack.domain.state

import blackjack.domain.card.Cards

abstract class Started(
    private val cards: Cards,
) : State {

    override fun cards(): Cards {
        return cards
    }
}
