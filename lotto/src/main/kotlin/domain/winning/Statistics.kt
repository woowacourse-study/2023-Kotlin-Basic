package domain.winning

import domain.lotto.Lotto
import domain.lotto.LottoBundle

class Statistics(
    lottoBundle: LottoBundle,
    winningLotto: WinningLotto
) {
    val rankAndCount: Map<Rank, Int>
    val profitRate: Double

    init {
        rankAndCount = countRank(lottoBundle, winningLotto)
        profitRate = calculateProfitRate(lottoBundle)
    }

    private fun countRank(lottoBundle: LottoBundle, winningLotto: WinningLotto): Map<Rank, Int> {
        return lottoBundle.lottos
            .map { Rank.match(it, winningLotto) }
            .groupBy { it }
            .map { (key, value) -> key to value.size }
            .toMap()
    }

    private fun calculateProfitRate(lottoBundle: LottoBundle): Double {
        val totalWinningMoney = rankAndCount
            .map { (key, value) -> key.money.value * value }
            .sum()
            .toDouble()

        val paidMoney = Lotto.LOTTO_PRICE * lottoBundle.lottos.size

        return totalWinningMoney / paidMoney
    }
}
