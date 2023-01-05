package domain.money

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row

class MoneyTest : StringSpec({
    "금액이 1000원 단위가 아니라면 예외가 발생한다." {
        forAll(
            row(1_001),
            row(999),
            row(15_100)
        ) { given ->
            shouldThrow<IllegalArgumentException> {
                Money(given)
            }
        }
    }
})
