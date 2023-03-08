package blackjack.domain.state

import blackjack.domain.JACK_SPACE
import blackjack.domain.KING_SPACE
import blackjack.domain.THREE_SPACE
import blackjack.domain.TWO_SPACE
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class BustTest {

    @Test
    fun `버스트 상태에서 draw는 불가능하다`() {
        val bust = Ready().draw(JACK_SPACE)
            .draw(KING_SPACE)
            .draw(TWO_SPACE)

        assertThatThrownBy { bust.draw(THREE_SPACE) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `버스트 상태에서 stay는 불가능하다`() {
        val bust = Ready().draw(JACK_SPACE)
            .draw(KING_SPACE)
            .draw(TWO_SPACE)

        assertThatThrownBy { bust.stay() }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `버스트 상태는 실행 불가능 상태이므로 실행 가능 유무는 false이다`() {
        val bust = Ready().draw(JACK_SPACE)
            .draw(KING_SPACE)
            .draw(TWO_SPACE)

        val result = bust.isRunning()

        assertThat(result).isFalse
    }

    @Test
    fun `버스트 상태의 끝난 상태 유무는 true이다`() {
        val bust = Ready().draw(JACK_SPACE)
            .draw(KING_SPACE)
            .draw(TWO_SPACE)

        val result = bust.isFinished()

        assertThat(result).isTrue
    }

    @Test
    fun `버스트 상태에서는 상대 상태에 상관없이 earning rate는 -1이다`() {
        val bust = Ready().draw(JACK_SPACE)
            .draw(KING_SPACE)
            .draw(TWO_SPACE)

        val otherBust = Ready().draw(JACK_SPACE)
            .draw(KING_SPACE)
            .draw(TWO_SPACE)

        val result = bust.earningRate(otherBust)

        assertThat(result).isEqualTo(-1.0)
    }
}
