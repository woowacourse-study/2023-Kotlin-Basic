package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Shape
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DealerTest {

    @Test
    fun `딜러는 추가 히트 이후 STAND 상태가 된다`() {
        // Given
        val dealer = Dealer(
            Card.from(Rank.TWO, Shape.CLOVER),
            Card.from(Rank.THREE, Shape.CLOVER),
        )

        // When
        dealer.hit(Card.from(Rank.EIGHT, Shape.CLOVER))

        // Then
        dealer.state shouldBe ParticipantState.STAND
    }

    @Test
    fun `딜러는 현재 가지고 있는 카드 합이 16를 초과하면 히트할 수 없다`() {
        // Given
        val dealer = Dealer(
            Card.from(Rank.KING, Shape.CLOVER),
            Card.from(Rank.QUEEN, Shape.CLOVER),
        )

        // When & Then
        shouldThrow<Exception> {
            dealer.hit(Card.from(Rank.EIGHT, Shape.CLOVER))
        }
    }

    @Test
    fun `딜러는 STAND 상태에서 히트할 수 없다`() {
        // Given
        val dealer = Dealer(
            Card.from(Rank.ACE, Shape.CLOVER),
            Card.from(Rank.TWO, Shape.CLOVER),
        )

        dealer.hit(Card.from(Rank.EIGHT, Shape.CLOVER))

        // When & Then
        shouldThrow<IllegalStateException> {
            dealer.hit(Card.from(Rank.EIGHT, Shape.CLOVER))
        }
    }
}
