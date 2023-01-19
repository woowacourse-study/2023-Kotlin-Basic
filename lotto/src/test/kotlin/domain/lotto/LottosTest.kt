package domain.lotto

import domain.money.Money
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class LottosTest : StringSpec({
    "로또 구입 금액과 수동 로또 목록을 전달하면, 자동 로또 목록이 자동 생성된 LottoBundle이 생성된다." {
        val inputMoney = Money(15_000)
        val manualLottos = listOf<Lotto>(
            Lotto.manual(1, 2, 3, 4, 5, 6),
            Lotto.manual(1, 2, 3, 4, 5, 6),
            Lotto.manual(1, 2, 3, 4, 5, 6),
            Lotto.manual(1, 2, 3, 4, 5, 6),
            Lotto.manual(1, 2, 3, 4, 5, 6)
        )

        val lottos = Lottos(inputMoney, manualLottos)

        lottos.lottoCount shouldBe 15
    }

})
