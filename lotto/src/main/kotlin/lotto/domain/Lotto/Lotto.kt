package lotto.domain.Lotto

import lotto.domain.result.JudgeResult

class Lotto(
    val numbers: Set<Number>
) {

    fun judgeWinning(winningLotto: Lotto, bonusNumber: Number): JudgeResult {
        var sameCount = 0
        var bonusWin = false

        for (number in numbers) {
            if (winningLotto.contains(number)) {
                sameCount += 1
            }
        }

        if (numbers.contains(bonusNumber)) {
            bonusWin = true
        }

        return JudgeResult(sameCount, bonusWin)
    }

    private fun contains(number: Number) =
        numbers.contains(number)

    companion object {
        fun ofNumbers(numbers: MutableList<Number>): Lotto {
            validateCount(numbers)
            return Lotto(numbers.sortedBy { it.value }.toSet())
        }

        fun ofInt(numbers: List<Int>): Lotto {
            return ofNumbers(numbers.map { Number(it) }
                .toMutableList())
        }

        private fun validateCount(numbers: MutableList<Number>) {
            if (numbers.toMutableSet().size != 6) {
                throw IllegalArgumentException("중복되지 않는 6개의 숫자여야 합니다.")
            }
        }
    }
}
