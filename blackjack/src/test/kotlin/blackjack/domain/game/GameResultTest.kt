package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Shape
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GameResultTest {

    @Test
    fun `딜러와 플레이어 리스트를 전달하면, 참가자의 매치 결과를 계산한다`() {
        // Given
        val dealer = Dealer(
            Card.from(Rank.FIVE, Shape.CLOVER),
            Card.from(Rank.SIX, Shape.CLOVER),
        )

        val loser = Player(
            "loser",
            Card.from(Rank.TWO, Shape.CLOVER),
            Card.from(Rank.THREE, Shape.CLOVER),
        )

        val draw = Player(
            "winner",
            Card.from(Rank.FIVE, Shape.CLOVER),
            Card.from(Rank.SIX, Shape.CLOVER),
        )

        val winner = Player(
            "winner",
            Card.from(Rank.SEVEN, Shape.CLOVER),
            Card.from(Rank.EIGHT, Shape.CLOVER),
        )

        // When
        val gameResult = GameResult(
            dealer,
            listOf(loser, draw, winner)
        )

        // Then
        assertSoftly(gameResult) {
            dealerResult.result[WinDrawLose.WIN] shouldBe 1
            dealerResult.result[WinDrawLose.DRAW] shouldBe 1
            dealerResult.result[WinDrawLose.LOSE] shouldBe 1

            playersResult.result[winner] shouldBe WinDrawLose.WIN
            playersResult.result[draw] shouldBe WinDrawLose.DRAW
            playersResult.result[loser] shouldBe WinDrawLose.LOSE
        }
    }
}
