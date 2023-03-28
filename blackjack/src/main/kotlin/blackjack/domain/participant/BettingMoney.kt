package blackjack.domain.participant

import java.math.BigDecimal
import java.math.RoundingMode

data class BettingMoney private constructor(
    val value: BigDecimal,
) {

    operator fun times(percent: Double): BettingMoney {
        val multiplied = BigDecimal.valueOf(percent)
        val result: BigDecimal = value.multiply(multiplied)
        return BettingMoney(result.setScale(0, RoundingMode.FLOOR))
    }

    operator fun plus(otherBettingMoney: BettingMoney): BettingMoney {
        return BettingMoney(value.add(otherBettingMoney.value).setScale(0, RoundingMode.FLOOR))
    }

    companion object {
        val ZERO: BettingMoney = BettingMoney(BigDecimal.ZERO)

        private const val MONEY_LENGTH = 4
        private const val MONEY_DIVIDE_STANDARD = "000"

        fun of(value: String): BettingMoney {
            require(value.length >= MONEY_LENGTH) { "배팅 금액은 1000원 이상입니다." }
            require(value.endsWith(MONEY_DIVIDE_STANDARD)) { "배팅 금액은 1000원으로 나누어 떨어져야 합니다." }
            return BettingMoney(BigDecimal(value))
        }
    }
}
