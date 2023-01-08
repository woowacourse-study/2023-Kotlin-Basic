package lotto.domain.result

class JudgeResult(
    private val sameCount: Int,
    private val bonusWin: Boolean
) {

    fun calculateRank(): Int {
        return when (sameCount) {
            6 -> 1
            5 -> {
                if (bonusWin) return 2
                return 3
            }
            4 -> 4
            else -> 5
        }
    }
}
