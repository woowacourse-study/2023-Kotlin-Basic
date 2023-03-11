package blackjack.domain.card

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
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

    @Test
    fun `버스트 되었지만, A를 1점으로 취급하면 버스트가 아닌 경우 A를 1점으로 계산한다`() {
        // Given
        val hand = Hand.init(
            Card.from(Rank.TEN, Shape.CLOVER),
            Card.from(Rank.TEN, Shape.HEART)
        )

        // When
        hand.add(Card.from(Rank.ACE, Shape.DIAMOND))

        // Then
        assertSoftly(hand) {
            score shouldBe 21
            bust shouldBe false
        }
    }

    @Test
    fun `ACE가 2장 이상 있더라도 최대 ACE 1장만 1점 취급할 수 있다`() {
        // Given
        val hand = Hand.init(
            Card.from(Rank.TEN, Shape.CLOVER),
            Card.from(Rank.ACE, Shape.HEART)
        )

        // When
        hand.add(Card.from(Rank.ACE, Shape.DIAMOND))

        // Then
        assertSoftly(hand) {
            score shouldBe 22
            bust shouldBe true
        }
    }
}
