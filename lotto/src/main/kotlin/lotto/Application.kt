package lotto

import lotto.domain.result.Judge
import lotto.domain.Lotto.Lotto
import lotto.domain.Lotto.LottoFactory
import lotto.domain.Lotto.Number
import lotto.view.*

fun main() {
    val purchaseMoney = inputBuyMoney()
    val purchaseLottos = LottoFactory.purchaseLottos(purchaseMoney)

    printPurchaseCount(purchaseLottos.size)
    printLottoTickets(purchaseLottos)

    val winningLotto = Lotto.fromInts(inputWinningLotto())
    val bonusNumber = Number(inputBonusNumber())

    val lottoResult = Judge.judgeResult(purchaseLottos, winningLotto, bonusNumber)
    printResult(lottoResult)
}
