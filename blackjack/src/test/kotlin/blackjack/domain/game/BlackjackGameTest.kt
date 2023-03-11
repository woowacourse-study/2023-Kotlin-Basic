package blackjack.domain.game

import blackjack.domain.testdouble.FakeDeck
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

internal class BlackjackGameTest {

    @Test
    fun `플레이어 목록을 전달 받으면, 게임이 생성되고 딜러와 플레이어 모두에게 카드를 두장씩 나눠준다`() {
        // Given
        val names = listOf("aa", "bb", "cc")

        // When
        val blackjackGame = BlackjackGame(names)

        // Then
        assertSoftly(blackjackGame) {
            participants shouldHaveSize 4
            participants[0].hand.cards shouldHaveSize 2
            participants[1].hand.cards shouldHaveSize 2
            participants[2].hand.cards shouldHaveSize 2
            participants[3].hand.cards shouldHaveSize 2
        }
    }

    @Test
    fun `setNextParticipant를 호출하면 다음 참가자로 턴이 변경된다`() {
        // Given
        val names = listOf("aa", "bb", "cc")
        val blackjackGame = BlackjackGame(names)

        // When
        val player1 = blackjackGame.participantToHit
        blackjackGame.setNextParticipant()

        val player2 = blackjackGame.participantToHit
        blackjackGame.setNextParticipant()

        val player3 = blackjackGame.participantToHit
        blackjackGame.setNextParticipant()

        val dealer = blackjackGame.participantToHit
        blackjackGame.setNextParticipant()

        // Then
        assertSoftly {
            player1.name shouldBe "aa"
            player2.name shouldBe "bb"
            player3.name shouldBe "cc"
            dealer.name shouldBe "딜러"
        }
    }

    @Test
    fun `플레이어 추가 히트 여부를 전달받아 각 플레이어에 히트한다`() {
        // Given
        val names = listOf("aa", "bb", "cc")
        val blackjackGame = BlackjackGame(names, FakeDeck())

        // When
        blackjackGame.playerHitOrStand(true) // aa
        blackjackGame.setNextParticipant()

        blackjackGame.playerHitOrStand(false) // bb
        blackjackGame.setNextParticipant()

        blackjackGame.playerHitOrStand(true) // cc
        blackjackGame.setNextParticipant()

        // Then
        assertSoftly(blackjackGame.participants) {
            it[0].hand.cards shouldHaveSize 3
            it[1].hand.cards shouldHaveSize 2
            it[2].hand.cards shouldHaveSize 3
        }
    }

    @Test
    fun `모든 플레이어가 스탠드한 상태에서 플레이어는 추가로 히트 or 스탠드 할 수 없다`() {
        // Given
        val names = listOf("aa", "bb", "cc")
        val blackjackGame = BlackjackGame(names)

        blackjackGame.playerHitOrStand(false) // aa
        blackjackGame.setNextParticipant()

        blackjackGame.playerHitOrStand(false) // bb
        blackjackGame.setNextParticipant()

        blackjackGame.playerHitOrStand(false) // cc
        blackjackGame.setNextParticipant()

        // When & Then
        shouldThrow<IllegalStateException> {
            blackjackGame.playerHitOrStand(true)
        }
    }

    @Test
    fun `모든 플레이어가 스탠드한 상태에서 딜러는 히트 or 스탠드 할 수 있다`() {
        // Given
        val names = listOf("aa", "bb", "cc")
        val fakeDeck = FakeDeck()
        val blackjackGame = BlackjackGame(names, fakeDeck)

        blackjackGame.playerHitOrStand(false) // aa
        blackjackGame.setNextParticipant()

        blackjackGame.playerHitOrStand(false) // bb
        blackjackGame.setNextParticipant()

        blackjackGame.playerHitOrStand(false) // cc
        blackjackGame.setNextParticipant()

        // When
        blackjackGame.dealerHitOrStand()

        // Then
        blackjackGame.dealer.hand.cards shouldHaveSize 3
    }
}
