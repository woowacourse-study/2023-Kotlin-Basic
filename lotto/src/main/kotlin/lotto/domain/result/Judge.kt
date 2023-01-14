package lotto.domain.result

import lotto.domain.Lotto.Lotto
import lotto.domain.Lotto.Number

object Judge {

    fun judgeResult(purchaseLottos: List<Lotto>, winningLotto: Lotto, bonusNumber: Number): Map<Int, Int> {
        val results = mutableMapOf(
            *(1..5)
                .map { it to 0 }
                .toTypedArray()
        )

        purchaseLottos
            .forEach {
                val rank = it.judgeWinning(winningLotto, bonusNumber).calculateRank()
                results.put(rank, results.getOrDefault(rank, 0) + 1)
            }

        return results
    }
}
