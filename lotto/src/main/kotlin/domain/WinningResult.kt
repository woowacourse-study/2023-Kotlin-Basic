package domain

class WinningResult private constructor(
    val result: Map<Rank, Int>
) {
    val totalReward: Int = result.map { it.key.reward * it.value }.sum()

    companion object {
        fun of(winningLotto: WinningLotto, purchasedLotteries: List<Lotto>): WinningResult {
            val result = mutableMapOf(*Rank.values().map { Pair(it, 0) }.toTypedArray())
            for (it in purchasedLotteries) {
                val rank = winningLotto.judgeRank(it)
                result[rank] = result[rank]?.plus(1) ?: 1
            }
            return WinningResult(result)
        }
    }
}
