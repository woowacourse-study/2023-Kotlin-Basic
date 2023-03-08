package blackjack.domain.participant

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class NameTest {

    @Test
    fun `Name을 생성한다`() {
        val name = "hyeoni"

        val result = Name(name)

        assertThat(result.value).isEqualTo(name)
    }

    @Test
    fun `Name이 공백인 경우 예외를 던진다`() {
        val name = ""

        assertThatThrownBy { Name(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
