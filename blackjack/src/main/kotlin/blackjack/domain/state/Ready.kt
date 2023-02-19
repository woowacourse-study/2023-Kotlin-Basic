package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import java.lang.IllegalStateException

class Ready(
    private val cards: Cards = Cards(),
) : Started(cards) {

    override fun draw(card: Card): State {
        cards.append(card)

        if (cards.isBlackjack()) {
            return Blackjack(cards)
        }

        if (cards.isReady()) {
            return Hit(cards)
        }

        return Ready(cards)
    }

    override fun stay(): State {
        throw IllegalStateException("스테이 상태로 변경할 수 없습니다.")
    }

    override fun isRunning(): Boolean {
        return false
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun earningRate(state: State): Double {
        throw IllegalStateException("수익율를 조회할 수 없습니다.")
    }
}
