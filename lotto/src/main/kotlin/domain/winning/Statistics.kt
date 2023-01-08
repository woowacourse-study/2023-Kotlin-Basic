package domain.winning

import domain.lotto.Lotto
import domain.lotto.LottoBundle

class Statistics(
    lottoBundle: LottoBundle,
    winningLotto: WinningLotto
) {
    val ranks: Map<Rank, Int>
    val profitRate: Double

    // TODO: refactor
    init {
        ranks = lottoBundle.lottos.map { Rank.match(it, winningLotto) }
            .groupBy { it }
            .map { (key, value) -> key to value.size }
            .toMap()

        val totalWinningMoney = ranks.map { (key, value) -> key.money.value * value }
            .reduce { acc, cur -> acc + cur }
        profitRate = totalWinningMoney.toDouble() / (Lotto.LOTTO_PRICE * lottoBundle.lottos.size)
    }
}
