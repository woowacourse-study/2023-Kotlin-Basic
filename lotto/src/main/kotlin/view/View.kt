package view

import LotteryState
import domain.LotteryResult
import domain.Prize

fun inputMoneyAmount(): Int {
    println("구입 금액을 입력해 주세요")
    val money = readln()
    requireIsIntAndNotBlank(money)

    return money.toInt()
}

fun inputLottoCount(): Int {
    println("수동으로 구매할 로또 수를 입력해 주세요")
    val count = readln()
    requireIsIntAndNotBlank(count)

    return count.toInt()
}

fun inputLottoNumbers(count: Int): List<List<Int>> {
    println("수동으로 구매할 번호를 입력해 주세요")

    val lottoNumbers = mutableListOf<List<Int>>()

    for (i in 1..count) {
        val numbers = readln().split(",")
        numbers.forEach { requireIsIntAndNotBlank(it) }

        lottoNumbers.add(numbers.map { it.toInt() })
    }

    return lottoNumbers
}

fun inputWinningLotto(): List<Int> {
    println("지난 주 당첨 번호를 입력해 주세요")
    val numbers = readln().split(",")
    numbers.forEach { requireIsIntAndNotBlank(it) }

    return numbers.map { it.toInt() }
}

fun inputBonusBall(): Int {
    println("보너스 볼을 입력해 주세요")
    val number = readln()
    requireIsIntAndNotBlank(number)

    return number.toInt()
}

private fun requireIsIntAndNotBlank(input: String) {
    require(input.toIntOrNull() != null) { "숫자만 입력하세요" }
    require(input.isNotBlank()) { "빈 값을 입력할 수 없습니다" }
}

fun outputLotteryStatus(lotteryState: LotteryState) {
    println("수동으로 ${lotteryState.manualLottos.size}장, 자동으로 ${lotteryState.autoLottos.size}장 구매했습니다")

    for (manualLotto in lotteryState.manualLottos) {
        println("[${manualLotto.state()}]")
    }
    for (autoLotto in lotteryState.autoLottos) {
        println("[${autoLotto.state()}]")
    }
}

fun outputLotteryResult(lotteryResult: LotteryResult) {
    println(
        """
            
        당첨 통계
        ---------
    """.trimIndent()
    )

    val prizes = lotteryResult.validPrizes
    for (prize in Prize.values().filter { it != Prize.FAIL }) {
        println("${prize.hits}개 일치 ${if (prize == Prize.SECOND) "보너스볼 일치 " else " "}" +
                "(${prize.reward}원) - ${prizes[prize] ?: 0}개)")
    }

    println("총 수익률은 ${lotteryResult.earningRate}입니다")
}
