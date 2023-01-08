package view

import domain.lotto.Lotto
import domain.lotto.LottoBundle
import domain.lotto.LottoNumber
import domain.money.Money
import domain.winning.Rank
import domain.winning.Rank.*
import domain.winning.Statistics
import domain.winning.WinningLotto
import kotlin.math.roundToInt

private const val LOTTO_NUMBER_DELIMITER = ", "

fun inputMoney(): Money {
    println("구입금액을 입력해 주세요.")
    return Money(inputDecimal())
}

fun inputManualLottoCount(): Int {
    println("수동으로 구매할 로또 수를 입력해 주세요.")
    return inputDecimal()
}

fun inputManualLottos(manualCount: Int): List<Lotto> {
    println("수동으로 구매할 번호를 입력해 주세요.")
    return (1..manualCount).map {
        inputLotto()
    }
}

fun printLottoBundle(lottoBundle: LottoBundle, manualLottoCount: Int) {
    println("수동으로 ${manualLottoCount}장, 자동으로 ${lottoBundle.lottos.size - manualLottoCount}개를 구매했습니다.")
    lottoBundle.lottos.forEach {
        println(it)
    }
}

fun inputWinningLotto(): WinningLotto {
    println("지난 주 당첨 번호를 입력해 주세요.")
    val inputLotto = inputLotto()

    println("보너스 볼을 입력해 주세요.")
    val bonusNumber = LottoNumber(input().toInt())

    return WinningLotto(inputLotto, bonusNumber)
}

fun printStatistics(statistics: Statistics) {
    println("당첨 통계\n---------")

    printSingleStatistics(statistics, FIFTH)
    printSingleStatistics(statistics, FOURTH)
    printSingleStatistics(statistics, THIRD)
    printSingleStatistics(statistics, SECOND)
    printSingleStatistics(statistics, FIRST)
    printProfitRate(statistics)
}

private fun printSingleStatistics(statistics: Statistics, rank: Rank) {
    var matchCount = rank.matchCount
    var bonusBallText = " "
    if (rank == SECOND) {
        matchCount--
        bonusBallText = ", 보너스 볼 일치"
    }

    println("${matchCount}개 일치${bonusBallText}(${rank.money.value}원)- ${statistics.ranks[rank] ?: 0}개")
}

private fun printProfitRate(statistics: Statistics) {
    var profit = "손해"
    if (statistics.profitRate > 1) {
        profit = "이익"
    } // if문 축약?
    val profitRate = (statistics.profitRate * 100.0).roundToInt() / 100.0

    println("총 수익률은 ${profitRate}입니다.(기준이 1이기 때문에 결과적으로 ${profit}라는 의미임)")
}

private fun input(): String {
    val input = readLine() ?: ""
    require(input.isNotBlank()) { "빈 문자열을 입력할 수 없습니다. 다시 입력해주세요." }

    return input
}

private fun inputDecimal(): Int {
    try {
        return input().toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("숫자만 입력할 수 있습니다.")
    }
}

private fun inputLotto(): Lotto {
    val input = input()

    val numbers = input.split(LOTTO_NUMBER_DELIMITER)
        .map { it.toInt() }
        .map { LottoNumber(it) }
        .toList()

    return Lotto(numbers)
}
