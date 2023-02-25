package blackjack.domain.game

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BlackjackGameTest {

    @Test
    fun `플레이어 목록을 전달 받으면, 게임이 생성되고 딜러와 플레이어 모두에게 카드를 두장씩 나눠준다`() {
        // Given
        val names = listOf("aa", "bb", "cc")

        // When
        val blackjackGame = BlackjackGame(names)

        // Then
        assertSoftly(blackjackGame) {
            dealer.hand.cards shouldHaveSize 2
            players shouldHaveSize 3
            players[0].hand.cards shouldHaveSize 2
            players[1].hand.cards shouldHaveSize 2
            players[2].hand.cards shouldHaveSize 2
        }
    }

    @Test
    fun `플레이어가 히트하면 다음 플레이어에게 차례가 넘어간다`() {
        // Given
        val names = listOf("aa", "bb", "cc")
        val blackjackGame = BlackjackGame(names)

        // When
        val player1 = blackjackGame.playerToHit
        blackjackGame.playerHit(true) // aa

        val player2 = blackjackGame.playerToHit
        blackjackGame.playerHit(false) // bb

        val player3 = blackjackGame.playerToHit
        blackjackGame.playerHit(true) // cc

        // Then
        assertSoftly {
            player1.name shouldBe "aa"
            player2.name shouldBe "bb"
            player3.name shouldBe "cc"
        }
    }

    @Test
    fun `플레이어 추가 히트 여부를 전달받아 각 플레이어에 히트한다`() {
        // Given
        val names = listOf("aa", "bb", "cc")
        val blackjackGame = BlackjackGame(names)

        // When
        blackjackGame.playerHit(true) // aa
        blackjackGame.playerHit(false) // bb
        blackjackGame.playerHit(true) // cc

        // Then
        assertSoftly(blackjackGame.players) {
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

        blackjackGame.playerHit(false) // aa
        blackjackGame.playerHit(false) // bb
        blackjackGame.playerHit(false) // cc

        // When & Then
        shouldThrow<IllegalStateException> {
            blackjackGame.playerHit(true)
        }
    }

    @Test
    fun `모든 플레이어가 스탠드한 상태에서 딜러는 히트 or 스탠드 할 수 있다`() {
        // Given
        val names = listOf("aa", "bb", "cc")
        val blackjackGame = BlackjackGame(names)

        blackjackGame.playerHit(false) // aa
        blackjackGame.playerHit(false) // bb
        blackjackGame.playerHit(false) // cc

        // When
        blackjackGame.dealerHitOrStand()

        // Then
        blackjackGame.dealer.hand.cards shouldHaveSize 3
    }

    @Test
    fun `모든 플레이어가 스탠드하지 않았다면 딜러는 히트 or 스탠드 할 수 없다`() {
        /*
         * TODO:
         *   아직 객체가 테스트할 수 없는 구조 (주어진 카드가 랜덤이다)
         *   테스트에서 Deck을 원하는 순서로 주입할 수 있어야 테스트 가능
         */
    }

    @Test
    fun `게임의 승패 결과를 반환한다`() {
        /*
         * TODO:
         *   아직 객체가 테스트할 수 없는 구조 (주어진 카드가 랜덤이다)
         *   테스트에서 Deck을 원하는 순서로 주입할 수 있어야 테스트 가능
         */
    }
}
