package blackjack.domain.state

import blackjack.domain.FIVE_HIT
import blackjack.domain.JACK_SPACE
import blackjack.domain.TWELVE_HIT
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class HitTest {

    @Test
    fun `히트_에서_히트`() {
        val hit = Hit(FIVE_HIT)

        val result = hit.draw(JACK_SPACE)

        assertThat(result)
            .isInstanceOf(Hit::class.java)
    }

    @Test
    fun `히트 상태에서 draw 한 뒤 카드의 합이 21을 초과하면 bust 상태이다`() {
        val hit = Hit(TWELVE_HIT)

        val result = hit.draw(JACK_SPACE)

        assertThat(result)
            .isInstanceOf(Bust::class.java)
    }

    @Test
    fun `히트 상태에서 stay를 실행할 경우 스테이 상태가 된다`() {
        val hit: State = Hit(TWELVE_HIT)

        assertThat(hit.stay())
            .isInstanceOf(Stay::class.java)
    }

    @Test
    fun `히트 상태는 실행 가능 상태이므로 실행 가능 유무는 true이다`() {
        val hit = Hit(TWELVE_HIT)

        val result = hit.isRunning()

        assertThat(result).isTrue
    }

    @Test
    fun `히트 상태에서 끝난 상태 유무는 false이다`() {
        val hit = Hit(TWELVE_HIT)

        val result = hit.isFinished()

        assertThat(result).isFalse
    }

    @Test
    fun `히트 상태에서 earning rate를 구할 수 없다`() {
        assertThatThrownBy { Hit(TWELVE_HIT).earningRate(Ready()) }
            .isInstanceOf(IllegalStateException::class.java)
    }
}
