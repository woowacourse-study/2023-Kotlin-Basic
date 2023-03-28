package blackjack.domain.state

import blackjack.domain.card.Cards

class Blackjack(
    cards: Cards,
) : Finished(cards) {

    override fun earningRate(state: State): Double {
        val otherCards = state.cards()
        if (otherCards.isBlackjack()) {
            return TIE_RATE
        }

        return BLACKJACK_WIN_RATE
    }
}
