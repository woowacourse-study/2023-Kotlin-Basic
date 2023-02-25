package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.game.WinDrawLose

interface Participant {
    val name: String
    val hand: Hand
    val score: Int
    var state: ParticipantState

    fun hit(card: Card): ParticipantState

    fun stand() {
        state = ParticipantState.STAND
    }

    fun match(other: Participant): WinDrawLose {
        return when {
            this.state == ParticipantState.BUST && other.state != ParticipantState.BUST -> WinDrawLose.LOSE
            this.state != ParticipantState.BUST && other.state == ParticipantState.BUST -> WinDrawLose.WIN
            this.score > other.score -> WinDrawLose.WIN
            this.score == other.score -> WinDrawLose.DRAW
            this.score < other.score -> WinDrawLose.LOSE
            else -> throw IllegalArgumentException("승부 결과를 판단할 수 없습니다.")
        }
    }
}
