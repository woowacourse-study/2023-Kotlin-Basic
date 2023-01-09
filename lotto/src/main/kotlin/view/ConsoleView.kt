package view

import domain.lotto.Lotto
import domain.lotto.LottoBundle
import domain.lotto.LottoNumber
import domain.money.Money
import domain.winning.Rank
import domain.winning.Rank.*
import domain.winning.Statistics
import domain.winning.WinningLotto

private const val LOTTO_INPUT_REGEX = "\\d?\\d, \\d?\\d, \\d?\\d, \\d?\\d, \\d?\\d, \\d?\\d"
private const val LOTTO_NUMBER_DELIMITER = ", "

fun inputMoney(): Money {
    println("구입금액을 입력해 주세요.")
    return Money(inputDecimal())
}

fun inputManualLottoCount(): Int {
    println("\n수동으로 구매할 로또 수를 입력해 주세요.")
    return inputDecimal()
}

fun inputManualLottos(manualCount: Int): List<Lotto> {
    println("\n수동으로 구매할 번호를 입력해 주세요.")
    return (1..manualCount).map {
        inputLotto()
    }
}

fun printLottoBundle(lottoBundle: LottoBundle, manualLottoCount: Int) {
    println("\n수동으로 ${manualLottoCount}장, 자동으로 ${lottoBundle.lottoCount - manualLottoCount}개를 구매했습니다.")
    lottoBundle.lottos.forEach {
        println(renderLottoText(it))
    }
}

fun inputWinningLotto(): WinningLotto {
    println("\n지난 주 당첨 번호를 입력해 주세요.")
    val inputLotto = inputLotto()

    println("보너스 볼을 입력해 주세요.")
    val bonusNumber = LottoNumber(input().toInt())

    return WinningLotto(inputLotto, bonusNumber)
}

fun printStatistics(statistics: Statistics) {
    println("\n당첨 통계\n---------")

    printSingleStatistics(statistics, FIFTH)
    printSingleStatistics(statistics, FOURTH)
    printSingleStatistics(statistics, THIRD)
    printSingleStatistics(statistics, SECOND)
    printSingleStatistics(statistics, FIRST)
    printProfitRate(statistics)
}

private fun printSingleStatistics(statistics: Statistics, rank: Rank) {
    val matchCount = if (rank == SECOND) rank.matchCount else rank.matchCount - 1
    val bonusBallText = if (rank == SECOND) ", 보너스 볼 일치" else " "

    println("${matchCount}개 일치${bonusBallText}(${rank.money.value}원)- ${statistics.rankAndCount[rank] ?: 0}개")
}

private fun printProfitRate(statistics: Statistics) {
    val profitOrLossText = if (statistics.profitRate > 1) "이익" else "손해"
    val profitRate = String.format("%.2f", statistics.profitRate).toDouble()

    println("총 수익률은 ${profitRate}입니다.(기준이 1이기 때문에 결과적으로 ${profitOrLossText}라는 의미임)")
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
    require(Regex(LOTTO_INPUT_REGEX).matches(input)) { "로또 입력 형식이 올바르지 않습니다. (예: 1, 2, 3, 4, 5, 6)" }

    val numbers = input.split(LOTTO_NUMBER_DELIMITER)
        .map { it.toInt() }
        .toIntArray()

    return Lotto.manual(*numbers)
}

private fun renderLottoText(lotto: Lotto): String {
    val lottoNumberText = lotto.values
        .map { it.value }
        .joinToString(LOTTO_NUMBER_DELIMITER)

    return "[${lottoNumberText}]"
}
