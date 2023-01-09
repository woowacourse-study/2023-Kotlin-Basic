package domain.winning

import domain.lotto.Lotto
import domain.lotto.Lottos

class Statistics(
    lottos: Lottos,
    winningLotto: WinningLotto
) {
    val rankAndCount: Map<Rank, Int>
    val profitRate: Double

    init {
        rankAndCount = countRank(lottos, winningLotto)
        profitRate = calculateProfitRate(lottos)
    }

    private fun countRank(lottos: Lottos, winningLotto: WinningLotto): Map<Rank, Int> {
        return lottos.lottos
            .map { Rank.determine(it, winningLotto) }
            .groupBy { it }
            .map { (key, value) -> key to value.size }
            .toMap()
    }

    private fun calculateProfitRate(lottos: Lottos): Double {
        val totalWinningMoney = rankAndCount
            .map { (rank, value) -> rank.prize.value * value }
            .sum()
            .toDouble()

        val paidMoney = Lotto.LOTTO_PRICE * lottos.lottoCount

        return totalWinningMoney / paidMoney
    }
}
