package lotto.domain.result

import lotto.domain.Lotto.Lotto
import lotto.domain.Lotto.Number

object Judge {

    fun judgeResult(purchaseLottos: List<Lotto>, winningLotto: Lotto, bonusNumber: Number): MutableMap<Int, Int> {
        val results = mutableMapOf<Int, Int>(Pair(1, 0), Pair(2, 0), Pair(3, 0), Pair(4, 0), Pair(5, 0))

        purchaseLottos
            .forEach {
                val rank = it.judgeWinning(winningLotto, bonusNumber).calculateRank()
                results.put(rank, results.getOrDefault(rank, 0) + 1)
            }

        return results
    }
}
