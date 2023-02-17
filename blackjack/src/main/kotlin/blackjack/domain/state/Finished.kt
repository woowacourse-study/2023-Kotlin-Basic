package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

const val BLACKJACK_WIN_RATE = 1.5
const val WIN_RATE = 1.0
const val TIE_RATE = 0.0
const val LOW_RATE = -1.0

abstract class Finished(
    cards: Cards,
) : Started(cards) {

    override fun draw(card: Card): State {
        throw IllegalStateException("카드를 뽑을 수 없는 상태입니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("스테이 상태로 변경할 수 없습니다.")
    }

    override fun isRunning(): Boolean {
        return true
    }

    override fun isFinished(): Boolean {
        return false
    }
}
