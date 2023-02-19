package blackjack.controller

import blackjack.domain.game.BlackjackGame
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
        while (!blackjackGame.isDealerHit) {
            val player = blackjackGame.playerToHit // TODO: player가 STAND 혹은 BUST라면 건너뛰도록 변경
            blackjackGame.playerHit(playerHitOrStandView(player))
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
