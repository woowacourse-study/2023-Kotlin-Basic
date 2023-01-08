package lotto.view

import lotto.domain.Lotto.Lotto

class OutputView {

    companion object {

        fun printPurchaseCount(size: Int) {
            println("${size}개를 구매했습니다.")
        }

        fun printLottoTickets(lottos: List<Lotto>) {
            lottos.forEach {
                println(it.numbers.joinToString(separator = ", ", prefix = "[", postfix = "]"))
            }
        }

        fun printResult(lottoResult: MutableMap<Int, Int>) {
            println("당첨 통계")
            println("---------")

            val sortedResult = lottoResult.entries.sortedByDescending { it.key }

            for (result in sortedResult) {
                println("${result.key} 등 당첨 ${result.value}개")
            }
        }
    }
}
