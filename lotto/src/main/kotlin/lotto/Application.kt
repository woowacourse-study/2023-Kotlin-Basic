package lotto

import lotto.domain.result.Judge
import lotto.domain.Lotto.Lotto
import lotto.domain.Lotto.LottoFactory
import lotto.domain.Lotto.Number
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val purchaseMoney = InputView.inputBuyMoney()
    val purchaseLottos = LottoFactory.purchaseLottos(purchaseMoney)

    OutputView.printPurchaseCount(purchaseLottos.size)
    OutputView.printLottoTickets(purchaseLottos)

    val winningLotto = Lotto.ofInt(InputView.inputWinningLotto())
    val bonusNumber = Number(InputView.inputBonusNumber())

    val lottoResult = Judge.judgeResult(purchaseLottos, winningLotto, bonusNumber)
    OutputView.printResult(lottoResult)
}
