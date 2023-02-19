package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

class Player(
    override val name: String,
    card1: Card,
    card2: Card
): Participant {
    override val hand: Hand = Hand.init(card1, card2)
    override val score: Int
        get() = hand.score
    override var state: ParticipantState = ParticipantState.HIT

    override fun hit(card: Card): ParticipantState {
        check(state == ParticipantState.HIT) { "참가자가 스탠드 혹은 버스트 상태라서 카드를 뽑을 수 없습니다." }

        hand.add(card).let {
            if (!it) state = ParticipantState.BUST
        }

        return state
    }
}
