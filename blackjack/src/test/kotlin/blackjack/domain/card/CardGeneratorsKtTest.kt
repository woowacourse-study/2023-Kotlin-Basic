package blackjack.domain.card

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class CardGeneratorsKtTest {

    @Test
    fun `randomGenerate()에서 생성된 카드의 크기는 52장이다`() {
        val cards = randomGenerate()

        val result = cards.size

        assertThat(result).isEqualTo(52)
    }
}
