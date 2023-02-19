package blackjack

import blackjack.controller.GameController

fun main() {
    GameController.startGame()
    GameController.dealCards()
    GameController.hitRound()
    GameController.dealerHitOrStand()
    GameController.result()
}
