package blackjack.domain.participant

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `플레이어는 1명 이상이어야 한다`() {
        assertDoesNotThrow { Players(mapOf("hyeoni" to "10000")) }
    }

    @Test
    fun `플레이어가 1명 보다 작은 경우 예외를 던진다`() {
        assertThatThrownBy { Players(mapOf()) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
