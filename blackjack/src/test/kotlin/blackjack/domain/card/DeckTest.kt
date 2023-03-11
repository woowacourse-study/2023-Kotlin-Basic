package blackjack.domain.card

import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `모든 카드가 중복되지 않게 하나씩 총 52개 생성된다`() {
        // Given
        val deck = DefaultDeck()

        // When
        val poppedCards = mutableSetOf<Card>()

        (1..52).forEach { _ ->
            poppedCards.add(deck.pop())
        }

        // Then
        poppedCards shouldHaveSize(52)
    }
}
