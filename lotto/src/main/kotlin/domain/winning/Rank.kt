package domain.winning

import domain.lotto.Lotto
import domain.money.Money

enum class Rank(
    private val matchCount: Int?,
    private val includeBonus: Boolean?,
    private val money: Money?
) {
    FIRST(6, false, Money(2_000_000_000)),
    SECOND(6, true, Money(30_000_000)),
    THIRD(5, true, Money(1_500_000)),
    FOURTH(4, true, Money(50_000)),
    FIFTH(3, true, Money(5_000)),
    NONE(null, null, null),
    ;

    // TODO: refactor
    companion object {
        fun match(lotto: Lotto, winningLotto: WinningLotto): Rank {
            return if (lotto.match(winningLotto.winningLotto) == 6) {
                FIRST
            } else if (lotto.match(winningLotto.winningLotto) == 5 && lotto.has(winningLotto.bonusNumber)) {
                SECOND
            } else if (lotto.match(winningLotto.winningLotto) == 5) {
                THIRD
            } else if (lotto.match(winningLotto.winningLotto) == 4) {
                FOURTH
            } else if (lotto.match(winningLotto.winningLotto) == 3) {
                FIFTH
            } else {
                NONE
            }
        }
    }
}
