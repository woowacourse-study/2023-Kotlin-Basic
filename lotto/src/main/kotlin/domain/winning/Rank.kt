package domain.winning

import domain.lotto.Lotto
import domain.money.Money

enum class Rank(
    val matchCount: Int,
    val includeBonus: Boolean,
    val money: Money
) {
    FIRST(6, false, Money(2_000_000_000)),
    SECOND(6, true, Money(30_000_000)),
    THIRD(5, true, Money(1_500_000)),
    FOURTH(4, true, Money(50_000)),
    FIFTH(3, true, Money(5_000)),
    NONE(0, false, Money(0)),
    ;

    // TODO: refactor
    companion object {
        fun match(lotto: Lotto, winningLotto: WinningLotto): Rank {
            return values().find { rank ->
                var matchCount = lotto.match(winningLotto.winningLotto)
                val hasBonus = lotto.has(winningLotto.bonusNumber)
                if (rank.includeBonus && hasBonus) {
                    matchCount++
                }

                rank.matchCount == matchCount
            } ?: NONE
        }
    }
}
