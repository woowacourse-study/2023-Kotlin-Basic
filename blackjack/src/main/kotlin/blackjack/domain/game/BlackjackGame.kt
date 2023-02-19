package blackjack.domain.game

import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.ParticipantState
import blackjack.domain.participant.Player

class BlackjackGame(
    playerNames: List<String>
) {
    val dealer: Dealer
    val players: List<Player>
    var playerToHit: Player
    val isDealerHit // 딜러가 히트할 차례인지 여부
        get() = players.all { it.state != ParticipantState.HIT }

    private val deck = Deck()

    init {
        dealer = Dealer(deck.pop(), deck.pop())
        players = playerNames.map { name ->
            Player(name, deck.pop(), deck.pop())
        }.also {
            playerToHit = it.first()
        }
    }

    /**
     * 현재 차례의 플레이어가 스탠드할지 히트할지 결정한다.
     *
     * @return 계속해서 playerHit를 호출할 수 있는지 여부를 반환한다
     */
    fun playerHit(hit: Boolean): Boolean {
        check(!isDealerHit) { "딜러가 히트할 차례입니다" }

        playerHitOrStand(hit)

        nextPlayerTurn()

        return !isDealerHit
    }

    private fun playerHitOrStand(hit: Boolean) {
        if (hit) {
            playerToHit.hit(deck.pop())
        } else {
            playerToHit.stand()
        }
    }

    private fun nextPlayerTurn() {
        val nextIndex = players.indexOf(playerToHit) + 1
        playerToHit = players[nextIndex % players.size]
    }

    /**
     * 딜러가 한장의 카드를 더 뽑거나 뽑지 않는다.
     *
     * @return 딜러가 카드를 뽑은 여부를 반환한다
     */
    fun dealerHitOrStand(): Boolean {
        check(players.all { it.state != ParticipantState.HIT }) {
            "히트할 수 있는 플레이어가 있다면, 딜러는 아무 행동도 할 수 없습니다."
        }

        if (dealer.canHit) {
            dealer.hit(deck.pop())
            return true
        }

        return false
    }

    fun generateResult(): GameResult {
        return GameResult(dealer, players)
    }
}
