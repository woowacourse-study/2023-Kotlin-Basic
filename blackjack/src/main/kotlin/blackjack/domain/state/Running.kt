package blackjack.domain.state

import blackjack.domain.card.Cards

abstract class Running(
    protected val cards: Cards,
) : Started(cards) {

    override fun isRunning(): Boolean {
        return true
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun earningRate(state: State): Double {
        throw IllegalStateException("수익율을 조회할 수 없습니다.")
    }
}
