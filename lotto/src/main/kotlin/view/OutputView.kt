package view

import domain.lotto.Lotto
import domain.lotto.Lottos
import domain.winning.Rank
import domain.winning.Statistics

fun printLottoBundle(lottos: Lottos, manualLottoCount: Int) {
    println("\n수동으로 ${manualLottoCount}장, 자동으로 ${lottos.lottoCount - manualLottoCount}개를 구매했습니다.")
    lottos.lottos.forEach {
        println(renderLottoText(it))
    }
}

private fun renderLottoText(lotto: Lotto): String {
    val lottoNumberText = lotto.values
        .map { it.value }
        .joinToString(LOTTO_NUMBER_DELIMITER)

    return "[${lottoNumberText}]"
}

fun printStatistics(statistics: Statistics) {
    println("\n당첨 통계\n---------")

    printSingleStatistics(statistics, 3, Rank.FIFTH)
    printSingleStatistics(statistics, 4, Rank.FOURTH)
    printSingleStatistics(statistics, 5, Rank.THIRD)
    printSingleStatistics(statistics, 6, Rank.SECOND)
    printSingleStatistics(statistics, 6, Rank.FIRST)
    printProfitRate(statistics)
}

private fun printSingleStatistics(statistics: Statistics, matchCount: Int, rank: Rank) {
    val bonusBallText = if (rank == Rank.SECOND) ", 보너스 볼 일치" else " "
    println("${matchCount}개 일치${bonusBallText}(${rank.prize.value}원)- ${statistics.rankAndCount[rank] ?: 0}개")
}

private fun printProfitRate(statistics: Statistics) {
    val profitOrLossText = if (statistics.profitRate > 1) "이익" else "손해"
    val profitRate = String.format("%.2f", statistics.profitRate).toDouble()

    println("총 수익률은 ${profitRate}입니다.(기준이 1이기 때문에 결과적으로 ${profitOrLossText}라는 의미임)")
}
