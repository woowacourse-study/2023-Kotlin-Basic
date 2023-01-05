package domain.lotto

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class LottoTest : StringSpec({
    "Int 6개를 전달받아 수동으로 생성한다." {
        shouldNotThrow<IllegalArgumentException> {
            Lotto.Factory.manual(1, 2, 3, 4, 5, 6)
        }
    }

    "수동으로 생성 시 전달하는 숫자 개수가 6개 미만이면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Lotto.Factory.manual(1, 2, 3, 4, 5)
        }
    }

    "수동으로 생성 시 전달하는 숫자 개수가 6개 초과라면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Lotto.Factory.manual(1, 2, 3, 4, 5, 6, 7)
        }
    }

    "수동으로 생성 시 중복된 숫자를 전달하면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Lotto.Factory.manual(1, 1, 3, 4, 5, 6)
        }
    }

    "자동으로 로또를 생성한다." {
        shouldNotThrow<IllegalArgumentException> {
            Lotto.Factory.auto()
        }
    }
})
