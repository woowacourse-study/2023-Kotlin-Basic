package domain

class LotteryResult(
    private val prizes: List<Prize>,
    private val trials: Int
) {
    val validPrizes: Map<Prize, Int>
        get() = prizes.filter { it != Prize.FAIL }.groupingBy { it }.eachCount()


    val earningRate: Double
        get() = totalReward().toDouble() / (trials * Lotto.PRICE).toDouble()

    private fun totalReward(): Int = prizes.sumOf { it.reward }
}
