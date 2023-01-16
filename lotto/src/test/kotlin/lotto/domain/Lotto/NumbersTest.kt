package lotto.domain.Lotto

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class NumbersTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 6, 45])
    fun `인자로 넘긴 만큼의 숫자가 나와야한다`(count: Int) {
        Numbers.getShuffledNumbers(count).size shouldBe(count)
    }
}
