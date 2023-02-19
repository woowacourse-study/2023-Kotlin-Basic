package blackjack.domain.participant

import blackjack.domain.state.Ready

class Player(
    name: String,
    money: String,
) : Participant(Name(name), Ready()) {

    private val bettingMoney: BettingMoney = BettingMoney.of(money)

    override fun isDrawable(): Boolean {
        return !state.isFinished()
    }

    fun stay() {
        state = state.stay()
    }

    fun calculateProfit(dealer: Participant): BettingMoney {
        val earningRate = state.earningRate(dealer.state)
        return bettingMoney * earningRate
    }
}
