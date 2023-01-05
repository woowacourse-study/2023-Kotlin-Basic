package domain.lotto

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LottoTest : StringSpec({
    "Int 6개를 전달받아 수동으로 생성한다." {
        shouldNotThrow<IllegalArgumentException> {
            Lotto.manual(1, 2, 3, 4, 5, 6)
        }
    }

    "수동으로 생성 시 전달하는 숫자 개수가 6개 미만이면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Lotto.manual(1, 2, 3, 4, 5)
        }
    }

    "수동으로 생성 시 전달하는 숫자 개수가 6개 초과라면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Lotto.manual(1, 2, 3, 4, 5, 6, 7)
        }
    }

    "수동으로 생성 시 중복된 숫자를 전달하면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Lotto.manual(1, 1, 3, 4, 5, 6)
        }
    }

    "자동으로 로또를 생성한다." {
        shouldNotThrow<IllegalArgumentException> {
            Lotto.auto()
        }
    }

    "match는 서로 다른 두 로또간 일치하는 번호 개수를 반환한다." {
        forAll(
            row(Lotto.manual(1, 2, 3, 4, 5, 6), 6),
            row(Lotto.manual(1, 2, 3, 4, 5, 45), 5),
            row(Lotto.manual(1, 2, 3, 4, 44, 45), 4),
            row(Lotto.manual(1, 2, 3, 43, 44, 45), 3),
            row(Lotto.manual(1, 2, 42, 43, 44, 45), 2),
            row(Lotto.manual(1, 41, 42, 43, 44, 45), 1),
            row(Lotto.manual(40, 41, 42, 43, 44, 45), 0)
        ) { lotto, expected ->
            lotto.match(Lotto.manual(1, 2, 3, 4, 5, 6)) shouldBe expected
        }
    }

    "has는 로또가 특정 숫자를 포함하고 있는지 여부를 반환한다." {
        forAll(
            row(Lotto.manual(1, 2, 3, 4, 5, 6), LottoNumber(6), true),
            row(Lotto.manual(1, 2, 3, 4, 5, 6), LottoNumber(45), false)
        ) { lotto, number, expected ->
            lotto.has(number) shouldBe expected
        }
    }
})
