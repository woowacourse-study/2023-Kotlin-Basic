package blackjack.domain.game

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.ParticipantState
import blackjack.domain.participant.Player

class GameResult(
    dealer: Dealer,
    players: List<Player>
) {
    val dealerResult = DealerResult()
    val playersResult = PlayersResult()

    init {
        for (player in players) {
            dealerResult.add(dealer.match(player))
            playersResult.add(player, player.match(dealer))
        }
    }
}

class DealerResult {
    val result = mutableMapOf<WinDrawLose, Int>()

    fun add(type: WinDrawLose) {
        result.compute(type) { _, value ->
            value?.plus(1) ?: 1
        }
    }
}

class PlayersResult {
    val result = mutableMapOf<Player, WinDrawLose>()

    fun add(player: Player, winDrawLose: WinDrawLose) {
        result[player] = winDrawLose
    }
}
