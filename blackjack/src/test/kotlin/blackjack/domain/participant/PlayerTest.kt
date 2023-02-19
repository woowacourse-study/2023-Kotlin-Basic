package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Shape
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `플레이어는 스탠드 시 STAND 상태가 된다`() {
        // Given
        val player = Player(
            "hudi",
            Card.from(Rank.QUEEN, Shape.CLOVER),
            Card.from(Rank.TWO, Shape.CLOVER),
        )

        // When
        player.stand()

        // Then
        player.state shouldBe ParticipantState.STAND
    }

    @Test
    fun `플레이어는 카드 합이 21이 넘을 때 BUST 상태가 된다`() {
        // Given
        val player = Player(
            "hudi",
            Card.from(Rank.QUEEN, Shape.CLOVER),
            Card.from(Rank.TWO, Shape.CLOVER),
        )

        // When
        player.hit(
            Card.from(Rank.KING, Shape.CLOVER)
        )

        // Then
        player.state shouldBe ParticipantState.BUST
    }

    @Test
    fun `플레이어는 STAND 상태에서 히트할 수 없다`() {
        // Given
        val player = Player(
            "hudi",
            Card.from(Rank.ACE, Shape.CLOVER),
            Card.from(Rank.TWO, Shape.CLOVER),
        )

        player.stand()

        // When & Then
        shouldThrow<IllegalStateException> {
            player.hit(Card.from(Rank.EIGHT, Shape.CLOVER))
        }
    }

    @Test
    fun `플레이어는 BUST 상태에서 히트할 수 없다`() {
        // Given
        val player = Player(
            "hudi",
            Card.from(Rank.QUEEN, Shape.CLOVER),
            Card.from(Rank.TWO, Shape.CLOVER),
        )

        player.hit(
            Card.from(Rank.KING, Shape.CLOVER)
        )

        // When & Then
        shouldThrow<IllegalStateException> {
            player.hit(Card.from(Rank.EIGHT, Shape.CLOVER))
        }
    }
}
