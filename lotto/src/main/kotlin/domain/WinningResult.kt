package domain

class WinningResult private constructor(
    val result: Map<Rank, Int>
) {
    val totalReward: Int = result.map { it.key.reward * it.value }.sum()

    companion object {
        fun of(winningLotto: WinningLotto, purchasedLotteries: List<Lotto>): WinningResult {
            val result = mutableMapOf(
                *Rank.values()
                    .map { it to 0 }
                    .toTypedArray()
            )
            result.putAll(purchasedLotteries.map { winningLotto.judgeRank(it) }
                .groupingBy { it }
                .eachCount()
            )
            return WinningResult(result)
        }
    }
}
