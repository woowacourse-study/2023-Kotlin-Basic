package view

import domain.Lotto
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

private fun readNumber(errorMessage: String): Int {
    val value = readln()
    require(value.isNumeric()) { errorMessage }
    return value.toInt()
}

fun readManualLottoNumbers(): List<Int> {
    println("수동으로 구매할 번호를 쉼표(,)로 구분해서 입력해 주세요.")
    val inputs = readln().split(LOTTO_NUMBER_DELIMITER).map { it.trim() }
    require(inputs.all { it.isNumeric() }) { "로또 번호는 숫자여야 합니다." }
    return inputs.map { it.toInt() }
}

fun printErrorMessage(errorMessage: String?) {
    println(errorMessage ?: "오류가 발생했습니다.")
}

fun printLotteries(lotteries: List<Lotto>) {
    lotteries.forEach { println(it.numbers.joinToString(", ", "[", "]")) }
}
