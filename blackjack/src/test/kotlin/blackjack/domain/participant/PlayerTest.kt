package blackjack.domain.participant

import blackjack.domain.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `이름과 베팅 금액이 주어지면 플레이어가 생성된다`() {
        assertDoesNotThrow { Player("hyeoni", "10000") }
    }

    @Test
    fun `플레이어는 현재 상태를 판단하여 준비가 완료되지 않으면 false를 반환한다`() {
        val player = Player("mat", "10000")
        val result = player.isReady()
        assertThat(result).isFalse
    }

    @Test
    fun `플레이어는 현재 상태를 판단하여 준비 완료이면 true를 반환한다`() {
        val player = Player("mat", "10000")
        player.hit(TWO_SPACE)
        player.hit(THREE_SPACE)

        val result = player.isReady()

        assertThat(result).isTrue
    }

    @Test
    fun `플레이어가 stay를 진행할 경우 더 이상 게임을 진행하지 않는다`() {
        val player = Player("mat", "10000")
        player.hit(KING_SPACE)
        player.hit(JACK_SPACE)

        player.stay()

        assertThat(player.isDrawable()).isFalse
    }

    @Test
    fun `플레이어는 현재 상태를 판단하여 게임의 종료 여부를 반환한다`() {
        val player = Player("mat", "10000")
        player.hit(KING_SPACE)
        player.hit(JACK_SPACE)
        player.hit(QUEEN_SPACE)

        val result = player.isDrawable()

        assertThat(result).isFalse
    }

    @Test
    fun `보유한 카드의 총점을 반환한다`() {
        val player = Player("mat", "10000")
        player.hit(KING_SPACE)
        player.hit(JACK_SPACE)

        val result = player.totalScore

        assertThat(result).isEqualTo(20)
    }

    @Test
    fun `플레이어가 블랙잭으로 승리한 경우 배팅 금액의 1_5배를 얻는다`() {
        val dealer = Dealer()
        dealer.hit(JACK_SPACE)
        dealer.hit(KING_SPACE)
        val player = Player("mat", "10000")

        player.hit(ACE_SPACE)
        player.hit(KING_SPACE)

        val result = player.calculateProfit(dealer)

        assertThat(result).isEqualTo(BettingMoney.of("15000"))
    }

    @Test
    fun `플레이어가 블랙잭으로 무승부 인경우 배팅 금액의 0배를 얻는다`() {
        val dealer = Dealer()
        dealer.hit(ACE_SPACE)
        dealer.hit(KING_SPACE)

        val player = Player("mat", "10000")
        player.hit(ACE_SPACE)
        player.hit(KING_SPACE)

        val result = player.calculateProfit(dealer)

        assertThat(result).isEqualTo(BettingMoney.ZERO)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러가 21점일 때 승인 경우 배팅 금액의 1_5배를 얻는다`() {
        val dealer = Dealer()
        dealer.hit(FIVE_SPACE)
        dealer.hit(KING_SPACE)
        dealer.hit(SIX_SPACE)

        val player = Player("mat", "10000")
        player.hit(ACE_SPACE)
        player.hit(KING_SPACE)

        val result = player.calculateProfit(dealer)

        assertThat(result).isEqualTo(BettingMoney.of("15000"))
    }

    @Test
    fun `플레이어가 블랙잭과 버스트가 아니고 점수를 비교하여 무승부인 경우 배팅 금액의 0배를 얻는다`() {
        val dealer = Dealer()
        dealer.hit(JACK_SPACE)
        dealer.hit(KING_SPACE)

        val player = Player("mat", "10000")
        player.hit(JACK_SPACE)
        player.hit(KING_SPACE)

        player.stay()
        val result = player.calculateProfit(dealer)

        assertThat(result).isEqualTo(BettingMoney.ZERO)
    }

    @Test
    fun `플레이어가 블랙잭과 버스트가 아니고 점수를 비교하여 승인 경우 배팅 금액의 1배를 얻는다`() {
        val dealer = Dealer()
        dealer.hit(TWO_SPACE)
        dealer.hit(KING_SPACE)

        val player = Player("mat", "10000")
        player.hit(JACK_SPACE)
        player.hit(KING_SPACE)

        player.stay()
        val result = player.calculateProfit(dealer)

        assertThat(result).isEqualTo(BettingMoney.of("10000"))
    }

    @Test
    fun `플레이어가 버스트인 경우 무조건 패이므로 배팅 금액의 -1배를 얻는다`() {
        val dealer = Dealer()
        dealer.hit(TWO_SPACE)
        dealer.hit(KING_SPACE)

        val player = Player("mat", "10000")
        player.hit(JACK_SPACE)
        player.hit(QUEEN_SPACE)
        player.hit(KING_SPACE)

        val result = player.calculateProfit(dealer)

        assertThat(result).isEqualTo(BettingMoney.of("-10000"))
    }

    @Test
    fun `플레이어와 딜러가 모두 버스트인 경우 무조건 플레이어 패이므로 배팅 금액의 -1배를 얻는다`() {
        val dealer = Dealer()
        dealer.hit(JACK_SPACE)
        dealer.hit(THREE_SPACE)
        dealer.hit(KING_SPACE)

        val player = Player("mat", "10000")
        player.hit(JACK_SPACE)
        player.hit(QUEEN_SPACE)
        player.hit(KING_SPACE)

        val result = player.calculateProfit(dealer)

        assertThat(result).isEqualTo(BettingMoney.of("-10000"))
    }

    @Test
    fun `플레이어가 블랙잭과 버스트가 아니고 점수를 비교하여 패인 경우 배팅 금액의 -1배를 얻는다`() {
        val dealer = Dealer()
        dealer.hit(JACK_SPACE)
        dealer.hit(THREE_SPACE)

        val player = Player("mat", "10000")
        player.hit(JACK_SPACE)
        player.hit(TWO_SPACE)

        player.stay()
        val result = player.calculateProfit(dealer)

        assertThat(result).isEqualTo(BettingMoney.of("-10000"))
    }
}
