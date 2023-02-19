package blackjack.domain.card

import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `모든 카드가 중복되지 않게 하나씩 총 52개 생성된다`() {
        // Given
        val deck = Deck()

        // When
        val poppedCards = mutableSetOf<Card>()

        for (ignored in 1..52) {
            poppedCards.add(deck.pop())
        }

        // Then
        poppedCards shouldHaveSize(52)
    }

    @Test
    fun `카드 순서가 랜덤하게 섞인다`() {
        // Given
        val decks = (1..10).map { Deck() }

        // When
        val cardsOfDecks = decks.map { deck ->
            (1..52).map { deck.pop() }
        }.toSet()

        // Then
        cardsOfDecks shouldHaveSize 10
    }
}
