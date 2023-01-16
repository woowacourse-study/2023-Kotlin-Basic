package lotto.domain.result

class JudgeResult(
    private val sameCount: Int,
    private val bonusWin: Boolean
) {

    fun calculateRank(): Int {
        return when {
            sameCount == 6 -> 1
            sameCount == 5 && bonusWin -> 2
            sameCount == 5 -> 3
            sameCount == 4 -> 4
            else -> 5
        }
    }
}
