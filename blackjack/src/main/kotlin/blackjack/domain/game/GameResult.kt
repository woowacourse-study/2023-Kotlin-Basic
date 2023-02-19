package blackjack.domain.game

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResult(
    dealer: Dealer,
    players: List<Player>
) {
    val dealerResult = DealerResult()
    val playersResult = PlayersResult()

    init {
        /*
            TODO: Participant 가 직접 승패를 결정하도록 변경
            TODO: score가 아무리 커도 BUST 되었으면 패배하도록 변경
        */
        for (player in players) {
            if (dealer.score > player.score) {
                playersResult.lose(player)
                dealerResult.win()
            }

            if (dealer.score == player.score) {
                playersResult.draw(player)
                dealerResult.draw()
            }

            if (dealer.score < player.score) {
                playersResult.win(player)
                dealerResult.lose()
            }
        }
    }
}

class DealerResult {
    val result = mutableMapOf<WinDrawLose, Int>()

    fun win() {
        add(WinDrawLose.WIN)
    }

    fun draw() {
        add(WinDrawLose.DRAW)
    }

    fun lose() {
        add(WinDrawLose.LOSE)
    }

    private fun add(type: WinDrawLose) {
        result.compute(type) { _, value ->
            value?.plus(1) ?: 1
        }
    }
}

class PlayersResult {
    val result = mutableMapOf<Player, WinDrawLose>()

    fun win(player: Player) {
        result[player] = WinDrawLose.WIN
    }

    fun draw(player: Player) {
        result[player] = WinDrawLose.DRAW
    }

    fun lose(player: Player) {
        result[player] = WinDrawLose.LOSE
    }
}
