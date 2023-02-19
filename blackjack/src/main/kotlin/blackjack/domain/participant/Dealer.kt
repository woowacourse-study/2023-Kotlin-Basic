package blackjack.domain.participant

import blackjack.domain.state.Ready
import blackjack.domain.state.State

private const val DEALER_DRAWABLE_SCORE = 16

class Dealer(
    name: Name = Name("딜러"),
    state: State = Ready(),
) : Participant(name, state) {

    override fun isDrawable(): Boolean {
        return totalScore <= DEALER_DRAWABLE_SCORE
    }
}
