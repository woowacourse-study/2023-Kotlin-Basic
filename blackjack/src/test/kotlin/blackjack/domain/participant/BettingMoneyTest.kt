package blackjack.domain.participant

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class BettingMoneyTest {

    @Test
    fun `문자열 정수가 주어지면 배팅 금액이 생성된다`() {
        assertDoesNotThrow() { BettingMoney.of("20000") }
    }

    @Test
    fun `1000 미만의 정수가 들어오면 예외를 던진다`() {
        assertThatThrownBy { BettingMoney.of("999") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `1000으로 나누어 떨어지지 않는 경우 예외를 던진다`() {
        assertThatThrownBy { BettingMoney.of("1999") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `베팅 머니에 실수를 곱한다`() {
        val bettingMoney = BettingMoney.of("1000")

        val result = bettingMoney * 2.0

        assertThat(result).isEqualTo(BettingMoney.of("2000"))
    }

    @Test
    fun `베팅 머니에 베팅 머니를 더한다`() {
        val bettingMoney = BettingMoney.of("1000")

        val result = bettingMoney + BettingMoney.of("2000")

        assertThat(result).isEqualTo(BettingMoney.of("3000"))
    }
}
