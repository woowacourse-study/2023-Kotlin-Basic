package blackjack.domain.participant

import blackjack.domain.JACK_SPACE
import blackjack.domain.KING_SPACE
import blackjack.domain.THREE_SPACE
import blackjack.domain.TWO_SPACE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러를 생성한다`() {
        assertDoesNotThrow { Dealer() }
    }

    @Test
    fun `딜러는 현재 상태를 판단하여 준비가 완료되지 않으면 false를 반환한다`() {
        val dealer = Dealer()

        val result = dealer.isReady()

        assertThat(result).isFalse
    }

    @Test
    fun `딜러는 현재 상태를 판단하여 준비가 완료되면 true를 반환한다`() {
        val dealer = Dealer()
        dealer.hit(TWO_SPACE)
        dealer.hit(THREE_SPACE)

        val result = dealer.isReady()

        assertThat(result).isTrue
    }

    @Test
    fun `플레이어는 현재 상태를 판단하여 게임의 종료 여부를 반환한다`() {
        val dealer = Dealer()
        dealer.hit(KING_SPACE)
        dealer.hit(JACK_SPACE)

        val result = dealer.isDrawable()

        assertThat(result).isFalse
    }

    @Test
    fun `보유한 카드의 총점을 반환한다`() {
        val dealer = Dealer()
        dealer.hit(KING_SPACE)
        dealer.hit(JACK_SPACE)

        val result = dealer.totalScore

        assertThat(result).isEqualTo(20)
    }
}
