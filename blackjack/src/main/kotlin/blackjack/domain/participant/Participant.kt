package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

interface Participant {
    val name: String
    val hand: Hand
    val score: Int
    var state: ParticipantState

    fun hit(card: Card): ParticipantState

    fun stand() {
        state = ParticipantState.STAND
    }
}
