package blackjack.controller

import blackjack.domain.game.BlackjackGame
import blackjack.domain.participant.ParticipantState
import blackjack.view.*

object GameController {

    // TODO: 에러 핸들링 및 재시도 로직 추가

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
        while (!blackjackGame.dealerHitTurn) {
            val player = blackjackGame.playerToHit
            val hitResult = blackjackGame.playerHit(playerHitOrStandView(player))

            participantCardsView(player)

            if (hitResult == ParticipantState.BUST) {
                playerBustView(player)
            }
        }
    }

    fun dealerHitOrStand() {
        val dealerHit = blackjackGame.dealerHitOrStand()
        if (dealerHit) {
            dealerHitView()
        }
    }

    fun result() {
        participantsCardsAndScoreView(
            blackjackGame.dealer,
            blackjackGame.players,
        )

        finalResultView(blackjackGame.generateResult())
    }
}
