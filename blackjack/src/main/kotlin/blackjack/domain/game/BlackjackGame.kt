package blackjack.domain.game

import blackjack.domain.card.Deck
import blackjack.domain.card.DefaultDeck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.ParticipantState
import blackjack.domain.participant.Player

class BlackjackGame(
    playerNames: List<String>,
    private val deck: Deck = DefaultDeck(),
) {
    val participants: List<Participant>

    val dealer: Dealer
        get() = participants.find { it is Dealer } as Dealer
    val players: List<Player>
        get() = participants.filterIsInstance<Player>()

    var participantToHit: Participant
    val hitRoundEnd
        get() = participants.all { it.state != ParticipantState.HIT }

    init {
        participants = playerNames.map { name ->
            Player(name, deck.pop(), deck.pop())
        } + Dealer(deck.pop(), deck.pop())

        participantToHit = participants.first()
    }

    fun playerHitOrStand(hit: Boolean) {
        check(!hitRoundEnd) { "참가자 중 아무도 히트할 수 없습니다." }
        check(participantToHit is Player) { "플레이어가 히트할 차례입니다." }

        if (hit) {
            participantToHit.hit(deck.pop())
        } else {
            participantToHit.stand()
            ParticipantState.STAND
        }
    }

    fun dealerHitOrStand(): Boolean {
        check(!hitRoundEnd) { "참가자 중 아무도 히트할 수 없습니다." }
        check(participantToHit is Dealer) { "딜러가 히트할 차례입니다." }

        val dealer = participants.last() as Dealer

        if (dealer.canHit) {
            dealer.hit(deck.pop())
            return true
        }

        return false
    }

    fun setNextParticipant() {
        do {
            val nextIndex = participants.indexOf(participantToHit) + 1
            participantToHit = participants[nextIndex % participants.size]
        } while (participantToHit.state != ParticipantState.HIT)
    }

    fun generateResult(): GameResult {
        return GameResult(dealer, players)
    }
}
