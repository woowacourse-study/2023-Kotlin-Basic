package blackjack.domain.state

import blackjack.domain.card.Cards

class Bust(
    cards: Cards,
) : Finished(cards) {

    override fun earningRate(state: State): Double {
        return LOW_RATE
    }
}
