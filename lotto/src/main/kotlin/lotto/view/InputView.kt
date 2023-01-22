package lotto.view


fun inputBuyMoney(): Long {
    println("구입금액을 입력해 주세요.")
    val input = readln()

    runCatching {
        return input.toLong()
    }.onFailure {
        println("숫자를 입력해주세요")
    }
    return inputBuyMoney()
}

fun inputWinningLotto(): List<Int> {
    println("지난 주 당첨 번호를 입력해 주세요.")
    val input = readln()

    runCatching {
        return input.split(", ")
            .map { it.toInt() }
    }.onFailure {
        println("콤마(,)로 구분된 1~45 사이의 숫자를 입력해주세요")
    }
    return inputWinningLotto()
}

fun inputBonusNumber(): Int {
    println("보너스 볼을 입력해주세요")
    val input = readln()

    runCatching {
        return input.toInt()
    }.onFailure {
        println("1~45 사이의 숫자를 입력해주세요")
    }
    return inputBonusNumber()
}
