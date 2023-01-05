package domain.lotto

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LottoNumberTest : StringSpec({
    "로또 번호가 1보다 작다면, 예외가 발생한다." {
        forAll(
            row(3),
            row(-1),
            row(-10),
            row(-100)
        ) { given ->
            shouldThrow<IllegalArgumentException> {
                LottoNumber(given)
            }
        }
    }

    "로또 번호는 45보다 크다면, 예외가 발생한다." {
        forAll(
            row(46),
            row(100),
            row(1000)
        ) { given ->
            shouldThrow<IllegalArgumentException> {
                LottoNumber(given)
            }
        }
    }

    "같은 숫자를 가진 LottoNumber간 동등성 비교 결과는 참 이어야한다." {
        forAll(
            row(1),
            row(10),
            row(20),
            row(30),
        ) { given ->
            run {
                val lottoNumber1 = LottoNumber(given)
                val lottoNumber2 = LottoNumber(given)

                (lottoNumber1 == lottoNumber2) shouldBe true
            }
        }
    }
})
