package blackjack.domain.participant

import java.math.BigDecimal

private val MINIMUM_MONEY: BigDecimal = BigDecimal.valueOf(1_000)
private val ZERO_MONEY: BigDecimal = BigDecimal(0)

data class BettingMoney private constructor(
    private val value: BigDecimal
) {

    init {
        require(value >= MINIMUM_MONEY) { "배팅 금액은 1000원 이상입니다." }
        require(value % MINIMUM_MONEY == ZERO_MONEY) { "배팅 금액은 1000원으로 나누어 떨어져야 합니다." }
    }

    constructor(value: String) : this(BigDecimal(value))
}
