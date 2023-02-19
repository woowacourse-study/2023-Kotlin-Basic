package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.state.State

abstract class Participant protected constructor(
    val name: Name,
    var state: State,
) {

    val totalScore: Int
        get() {
            val cards = state.cards()
            return cards.totalScore()
        }

    val cards: List<Card>
        get() {
            return state.cards().value
        }

    fun isReady(): Boolean {
        return state.isRunning()
    }

    fun hit(card: Card) {
        if (isDrawable()) {
            state = state.draw(card)
        }
    }

    abstract fun isDrawable(): Boolean
}
