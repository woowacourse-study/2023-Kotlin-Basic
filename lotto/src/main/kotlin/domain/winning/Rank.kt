package domain.winning

import domain.lotto.Lotto
import domain.money.Money

enum class Rank(
    val prize: Money
) {
    FIRST(Money(2_000_000_000)),
    SECOND(Money(30_000_000)),
    THIRD(Money(1_500_000)),
    FOURTH(Money(50_000)),
    FIFTH(Money(5_000)),
    NONE(Money(0)),
    ;

    companion object {
        fun determine(lotto: Lotto, winningLotto: WinningLotto): Rank {
            val matchCount = lotto.matchCount(winningLotto.winningLotto)
            val hasBonusNumber = lotto.has(winningLotto.bonusNumber)

            return when {
                matchCount == 6 -> FIRST
                matchCount == 5 && hasBonusNumber -> SECOND
                matchCount == 5 -> THIRD
                matchCount == 4 -> FOURTH
                matchCount == 3 -> FIFTH
                else -> NONE
            }
        }
    }
}
