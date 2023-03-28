package blackjack.domain.state

import blackjack.domain.ACE_SPACE
import blackjack.domain.JACK_SPACE
import blackjack.domain.TWO_SPACE
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class ReadyTest {

    @Test
    fun `Card 한장만 가지고 있으면 Ready 상태를 유지한다`() {
        val ready = Ready()

        val result = ready.draw(JACK_SPACE)

        assertThat(result).isInstanceOf(Ready::class.java)
    }

    @Test
    fun `Card를 두장 가지고 있으면 준비가 완료되어 Hit 상태로 변경된다`() {
        val ready = Ready()

        val result = ready.draw(JACK_SPACE)
            .draw(TWO_SPACE)

        assertThat(result).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Card 두장의 합이 21이면 블랙잭 상태로 변경된다`() {
        val ready = Ready()

        val result = ready.draw(JACK_SPACE)
            .draw(ACE_SPACE)

        assertThat(result).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `레디 상태는 stay할 수 없다`() {
        val ready = Ready()

        assertThatThrownBy { ready.stay() }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `레디 상태는 실행 불가능 상태이므로 실행 가능 유무는 false이다`() {
        val ready = Ready().draw(JACK_SPACE)

        val result = ready.isRunning()

        assertThat(result).isFalse
    }

    @Test
    fun `레디 상태의 끝난 상태 유무는 false이다`() {
        val ready = Ready()

        val result = ready.isFinished()

        assertThat(result).isFalse
    }

    @Test
    fun `레디 상태에서 earning rate를 구할 수 없다`() {
        assertThatThrownBy { Ready().earningRate(Ready()) }
            .isInstanceOf(IllegalStateException::class.java)
    }
}
