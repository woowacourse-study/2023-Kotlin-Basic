package blackjack.domain.card

import blackjack.domain.card.Denomination.*
import blackjack.domain.card.Suit.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.lang.IllegalArgumentException

class DeckTest {

    @Test
    fun `카드 컬렉션을 가진 덱을 생성한다`() {
        assertDoesNotThrow { Deck(::randomGenerate) }
    }

    @Test
    fun `카드덱이 비어있는 경우 예외를 던진다`() {
        val deck = Deck { emptyList() }

        assertThatThrownBy { deck.pick() }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `카드덱에서 카드 한장을 뽑는다`() {
        val card = Card(ACE, DIAMOND)
        val deck = Deck { listOf(card) }

        val result = deck.pick()

        assertThat(result).isEqualTo(card)
    }
}
