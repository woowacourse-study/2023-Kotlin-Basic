package lotto.domain.Lotto

import lotto.domain.result.JudgeResult

class Lotto(
    val numbers: Set<Number>
) {

    init {
        validateCount(numbers)
    }

    fun judgeWinning(winningLotto: Lotto, bonusNumber: Number): JudgeResult {
        var sameCount = numbers.count { winningLotto.numbers.contains(it) }
        var bonusWin = numbers.contains(bonusNumber)

        return JudgeResult(sameCount, bonusWin)
    }

    private fun contains(number: Number) =
        numbers.contains(number)

    companion object {
        private const val LOTTO_SIZE = 6

        fun fromNumbers(numbers: List<Number>): Lotto {
            return Lotto(numbers.sortedBy { it.value }.toSet())
        }

        fun fromInts(numbers: List<Int>): Lotto {
            return fromNumbers(numbers.map { Number(it) }
                .toList())
        }
    }

    private fun validateCount(numbers: Set<Number>) {
        if (numbers.size != LOTTO_SIZE) {
            throw IllegalArgumentException("중복되지 않는 6개의 숫자여야 합니다.")
        }
    }
}
