package blackjack.domain.card

import blackjack.domain.BLACKJACK
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `카드 내부의 score을 모두 더한다`() {
        val cards = BLACKJACK

        val result = cards.totalScore()

        assertThat(result).isEqualTo(21)
    }
}
