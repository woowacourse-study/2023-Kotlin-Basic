package blackjack.domain.card

import blackjack.domain.ACE_SPACE
import blackjack.domain.BLACKJACK
import blackjack.domain.BUST
import blackjack.domain.FIVE_HIT
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `블랙잭인지 판별한다`() {
        val result = BLACKJACK.isBlackjack()

        assertThat(result).isTrue
    }

    @Test
    fun `카드의 크기가 2장이면 준비 완료이다`() {
        val result = FIVE_HIT.isReady()

        assertThat(result).isTrue
    }

    @Test
    fun `카드의 크기가 1장이면 준비가 완료되지 않았다`() {
        val cards = Cards(mutableListOf(ACE_SPACE))

        val result = cards.isReady()

        assertThat(result).isFalse
    }

    @Test
    fun `카드의 합이 21보다 큰 경우 버스트이다`() {
        val result = BUST.isBust()

        assertThat(result).isTrue
    }

    @Test
    fun `카드 내부의 score을 모두 더한다`() {
        val cards = BLACKJACK

        val result = cards.totalScore()

        assertThat(result).isEqualTo(21)
    }
}
