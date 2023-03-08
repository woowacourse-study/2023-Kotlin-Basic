package blackjack.domain.state

import blackjack.domain.BLACKJACK
import blackjack.domain.JACK_SPACE
import blackjack.domain.KING_SPACE
import blackjack.domain.TWO_SPACE
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BlackjackTest {

    @Test
    fun `블랙잭 상태는 두장의 카드가 21점인 경우 정상 생성된다`() {
        assertDoesNotThrow { Blackjack(BLACKJACK) }
    }

    @Test
    fun `블랙잭 상태에서 추가적인 카드 뽑기는 불가능하다`() {
        val blackjack = Blackjack(BLACKJACK)

        assertThatThrownBy { blackjack.draw(TWO_SPACE) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `블랙잭 상태에서 stay할 수 없다`() {
        val blackjack = Blackjack(BLACKJACK)

        assertThatThrownBy { blackjack.stay() }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `블랙잭 상태는 실행 불가능 상태이므로 실행 가능 유무는 false이다`() {
        val blackjack = Blackjack(BLACKJACK)

        val result = blackjack.isRunning()

        assertThat(result).isFalse
    }

    @Test
    fun `블랙잭 상태의 끝난 상태 유무는 true이다`() {
        val blackjack = Blackjack(BLACKJACK)

        val result = blackjack.isFinished()

        assertThat(result).isTrue
    }

    @Test
    fun `상대 상태가 블랙잭이면 무승부이므로 earning rate는 0이다`() {
        val blackjack: State = Blackjack(BLACKJACK)
        val otherBlackjack: State = Blackjack(BLACKJACK)

        val result = blackjack.earningRate(otherBlackjack)

        assertThat(result).isEqualTo(0.0)
    }

    @Test
    fun `상대 상태가 블랙잭이 아니면 승이므로 earning rate는 1_5이다`() {
        val blackjack = Blackjack(BLACKJACK)
        val otherState = Ready()
            .draw(KING_SPACE)
            .draw(JACK_SPACE)

        val result = blackjack.earningRate(otherState)

        assertThat(result).isEqualTo(1.5)
    }
}
