package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

private const val DEALER_HIT_THRESHOLD = 16
private const val DEALER_NAME = "딜러"

class Dealer(card1: Card, card2: Card): Participant {
    override val name: String = DEALER_NAME
    override val hand: Hand = Hand.init(card1, card2)
    override val score: Int
        get() = hand.score
    override var state: ParticipantState = ParticipantState.HIT

    val canHit: Boolean
        get() = score <= DEALER_HIT_THRESHOLD

    override fun hit(card: Card): ParticipantState {
        check(state == ParticipantState.HIT) { "참가자가 스탠드 혹은 버스트 상태라서 카드를 뽑을 수 없습니다." }
        check(canHit) { "딜러는 초기 점수가 ${DEALER_HIT_THRESHOLD}이하일 때만 히트할 수 있습니다." }

        hand.add(card)

        return if (hand.bust) {
            ParticipantState.BUST
        } else {
            ParticipantState.STAND
        }.also {
            state = it
        }
    }
}
