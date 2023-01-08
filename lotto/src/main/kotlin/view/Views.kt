package view

import util.isNumeric

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

fun printErrorMessage(errorMessage: String?) {
    println(errorMessage ?: "오류가 발생했습니다.")
}
