package view

import domain.Lotto
import domain.Rank
import domain.Rank.*
import domain.ReturnRate
import domain.WinningResult
import util.isNumeric

private const val LOTTO_NUMBER_DELIMITER = ","

fun readPurchaseMoney(): Int {
    println("구입 금액을 입력해 주세요.")
    return readNumber("구입 금액은 숫자여야 합니다.")
}

fun readManualCount(): Int {
    println("수동으로 구매할 로또 수를 입력해 주세요.")
    return readNumber("구매할 로또 수는 숫자여야 합니다.")
}

fun readManualLottoNumbers(): List<Int> {
    println("수동으로 구매할 번호를 쉼표(,)로 구분해서 입력해 주세요.")
    return readNumbers("로또 번호는 숫자여야 합니다.")
}

fun readWinningNumbers(): List<Int> {
    println("지난 주 당첨 번호를 입력해 주세요.")
    return readNumbers("당첨 번호는 숫자여야 합니다.")
}

fun readBonusBall(): Int {
    println("보너스 볼을 입력해 주세요.")
    return readNumber("보너스 볼은 숫자여야 합니다.")
}

private fun readNumber(errorMessage: String): Int {
    val value = readln()
    require(value.isNumeric()) { errorMessage }
    return value.toInt()
}

private fun readNumbers(errorMessage: String): List<Int> {
    val inputs = readln().split(LOTTO_NUMBER_DELIMITER).map { it.trim() }
    require(inputs.all { it.isNumeric() }) { errorMessage }
    return inputs.map { it.toInt() }
}

fun printErrorMessage(errorMessage: String?) {
    println(errorMessage ?: "오류가 발생했습니다.")
}

fun printLotteries(lotteries: List<Lotto>, manualCount: Int, autoCount: Int) {
    println("수동으로 ${manualCount}장, 자동으로 ${autoCount}장 구매했습니다.")
    lotteries.forEach { println(it.numbers.joinToString(", ", "[", "]")) }
}

fun printWinningResult(winningResult: WinningResult) {
    fun getMatchingInfo(rank: Rank): String = when (rank) {
        FIRST -> "6개 일치, (${FIRST.reward})원"
        SECOND -> "5개 일치, 보너스 볼 일치, (${SECOND.reward})원"
        THIRD -> "5개 일치, (${THIRD.reward})원"
        FOURTH -> "4개 일치, (${FOURTH.reward})원"
        FIFTH -> "3개 일치, (${FIFTH.reward})원"
        FAILED -> ""
    }

    println(
        """
        당첨 통계
        ${"-".repeat(10)}
        """.trimIndent()
    )

    winningResult.result.forEach { (rank, value) ->
        if (rank === FAILED) {
            return
        }
        println("${getMatchingInfo(rank)}- ${value}개")
    }
}

fun printReturnRate(returnRate: ReturnRate) {
    println("총 수익률은 ${String.format("%.2f", returnRate.value)}입니다.")
}
