package domain.winning

import domain.lotto.Lotto

class Statistics(
    lottos: List<Lotto>,
    winningLotto: WinningLotto
) {
    val ranks: Map<Rank, Int>
    val profitRate: Double

    // TODO: refactor
    init {
        ranks = lottos.map { Rank.match(it, winningLotto) }
            .groupBy { it }
            .map { (key, value) -> key to value.size }
            .toMap()

        val totalWinningMoney = ranks.map { (key, value) -> key.money.value * value }
            .reduce { acc, cur -> acc + cur }
        profitRate = totalWinningMoney.toDouble() / (Lotto.LOTTO_PRICE * lottos.size)
    }
}
