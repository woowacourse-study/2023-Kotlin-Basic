package lotto.domain.Lotto

import io.kotest.assertions.throwables.shouldThrow
import lotto.domain.Lotto.Number
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class NumberTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `숫자는 1~45사이여야한다`(value: Int) {
        shouldThrow<IllegalArgumentException> {
            Number(value)
        }
    }
}
