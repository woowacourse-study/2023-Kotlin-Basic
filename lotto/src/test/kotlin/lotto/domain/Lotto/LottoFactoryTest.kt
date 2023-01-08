package lotto.domain.Lotto

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class LottoFactoryTest {

    @ParameterizedTest
    @MethodSource("generateUnitConditions")
    fun `로또 한장의 가격은 1000원이다`(
        inputMoney: Long,
        expected: Int
    ) {
        val purchaseLottos = LottoFactory.purchaseLottos(inputMoney)

        purchaseLottos.size shouldBe expected
    }

    companion object {

        @JvmStatic
        fun generateUnitConditions(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1000, 1),
                Arguments.of(1500, 1),
                Arguments.of(2000, 2),
            )
        }
    }
}
