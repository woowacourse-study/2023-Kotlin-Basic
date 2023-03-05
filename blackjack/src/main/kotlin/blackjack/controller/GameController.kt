package blackjack.controller

import blackjack.domain.game.BlackjackGame
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.ParticipantState
import blackjack.domain.participant.Player
import blackjack.view.*

object GameController {

    private lateinit var blackjackGame: BlackjackGame

    fun startGame() {
        val names = startGameView()
        blackjackGame = BlackjackGame(names)
    }

    fun dealCards() {
        initialDealCardsView(
            blackjackGame.dealer,
            blackjackGame.players,
        )
    }

    fun hitRound() {
        while (true) {
            val participant = blackjackGame.participantToHit

            when(participant) {
                is Player -> {
                    retryOnFailure { blackjackGame.playerHitOrStand(playerHitOrStandView(participant)) }
                }
                is Dealer -> {
                    val dealerHit = blackjackGame.dealerHitOrStand()
                    if (dealerHit) dealerHitView()
                }
            }

            participantCardsView(participant)

            if (participant.state == ParticipantState.BUST) {
                participantBustView(participant)
            }

            if (blackjackGame.hitRoundEnd) break

            blackjackGame.setNextParticipant()
        }
    }

    fun result() {
        participantsCardsAndScoreView(
            blackjackGame.dealer,
            blackjackGame.players,
        )

        finalResultView(blackjackGame.generateResult())
    }

    private fun retryOnFailure(operation: () -> Unit) {
        try {
            operation()
        } catch (e: Exception) {
            println(e.message)
            retryOnFailure(operation)
        }
    }
}
