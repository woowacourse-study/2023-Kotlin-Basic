package blackjack.domain.card

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `data class는 equals(), hashcode() 메서드가 자동으로 생성된다`() {
        val card = Card(Denomination.ACE, Suit.DIAMOND)
        val other = Card(Denomination.ACE, Suit.DIAMOND)

        val result = card == other // equals() 비교

        assertThat(result).isTrue
    }

    @Test
    fun `score를 계산한다`() {
        val card = Card(Denomination.ACE, Suit.DIAMOND)

        val result = card.calculateScore(10)

        assertThat(result).isEqualTo(21)
    }
}
