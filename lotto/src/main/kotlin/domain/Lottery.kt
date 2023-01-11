package domain

import LotteryState

class Lottery(private val money: Money) {

    private var manualLottos: MutableList<Lotto> = mutableListOf()
    private var autoLottos: MutableList<Lotto> = mutableListOf()

    fun canBuyLottosOf(count: Int): Boolean {
        return money.isPayable(count * Lotto.PRICE)
    }

    fun addManualLottos(lottos: List<Lotto>) {
        money.use(Lotto.PRICE * lottos.size)
        manualLottos.addAll(lottos)
    }

    fun generateAutoLottos() {
        while (money.isPayable(Lotto.PRICE)) {
            money.use(Lotto.PRICE)
            autoLottos.add(Lotto(AutoLottoStrategy()))
        }
    }

    fun state(): LotteryState {
        return LotteryState(manualLottos, autoLottos)
    }

    fun scoredBy(winningLotto: WinningLotto): LotteryResult {
        val prizes = (manualLottos + autoLottos).map { winningLotto.score(it) }
        val trials = manualLottos.size + autoLottos.size
        return LotteryResult(prizes, trials)
    }
}

class Money(private var amount: Int) {

    init {
        require(amount >= Lotto.PRICE) { "금액은 ${Lotto.PRICE}원부터 가능합니다" }
    }

    fun use(price: Int) {
        require(amount - price >= 0) { "금액이 모자라 구매할 수 없습니다" }
        amount -= price
    }

    fun isPayable(price: Int): Boolean {
        return amount >= price
    }
}
