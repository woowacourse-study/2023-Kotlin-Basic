package blackjack.domain.state

import blackjack.domain.card.Cards

class Stay(
    cards: Cards,
) : Finished(cards) {

    override fun earningRate(state: State): Double {
        val otherCards = state.cards()
        return when {
            isWin(otherCards) -> WIN_RATE
            isTie(otherCards) -> TIE_RATE
            else -> LOW_RATE
        }
    }

    private fun isWin(otherCards: Cards): Boolean {
        return otherCards.isBust() || cards().totalScore() > otherCards.totalScore()
    }

    private fun isTie(otherCards: Cards): Boolean {
        return cards().totalScore() == otherCards.totalScore()
    }
}
