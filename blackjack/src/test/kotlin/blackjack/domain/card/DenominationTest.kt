package blackjack.domain.card

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class DenominationTest {

    @Test
    fun `현재 점수를 기반으로 끗수의 숫자를 더해 반환한다`() {
        val three = Denomination.THREE

        val result = three + 10

        assertThat(result).isEqualTo(13)
    }

    @Test
    fun `현재 끗수가 ACE일 때, 점수 합산이 21점 이하인 경우 11을 더한다`() {
        val ace = Denomination.ACE

        val result = ace + 5

        assertThat(result).isEqualTo(16)
    }

    @Test
    fun `현재 끗수가 ACE일 때, 점수 합산이 21 초과인 경우 1을 더한다`() {
        val ace = Denomination.ACE

        val result = ace + 21

        assertThat(result).isEqualTo(22)
    }
}
