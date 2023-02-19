package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

internal class HandTest {

    @Test
    fun `Hand에 중복된 카드를 넣을 수 없다`() {
        // Given
        val card1 = Card.from(Rank.ACE, Shape.CLOVER)
        val card2 = Card.from(Rank.TWO, Shape.CLOVER)

        val hand = Hand.init(card1, card2)

        // When & Then
        shouldThrow<IllegalArgumentException> {
            hand.add(card1)
        }
    }
}
