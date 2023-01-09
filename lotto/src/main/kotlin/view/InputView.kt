package view

import domain.lotto.Lotto
import domain.lotto.LottoCount
import domain.lotto.LottoNumber
import domain.money.Money
import domain.winning.WinningLotto

private const val LOTTO_INPUT_REGEX = "\\d?\\d, \\d?\\d, \\d?\\d, \\d?\\d, \\d?\\d, \\d?\\d"
const val LOTTO_NUMBER_DELIMITER = ", "

fun inputMoney(): Money {
    println("구입금액을 입력해 주세요.")
    return Money(inputDecimal())
}

fun inputManualLottoCount(): LottoCount {
    println("\n수동으로 구매할 로또 수를 입력해 주세요.")
    return LottoCount(inputDecimal())
}

fun inputManualLottos(manualCount: LottoCount): List<Lotto> {
    println("\n수동으로 구매할 번호를 입력해 주세요.")
    return (1..manualCount.value).map {
        inputLotto()
    }
}

fun inputWinningLotto(): WinningLotto {
    println("\n지난 주 당첨 번호를 입력해 주세요.")
    val inputLotto = inputLotto()

    println("보너스 볼을 입력해 주세요.")
    val bonusNumber = LottoNumber(input().toInt())

    return WinningLotto(inputLotto, bonusNumber)
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
