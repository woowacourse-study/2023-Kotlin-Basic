package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class LottoTest : StringSpec({

    "로또 숫자는 0 이하 46 이상이 될 수 없다" {
        listOf(-1, 0, 46).forAll { invalidNumber ->
            shouldThrow<IllegalArgumentException> { Ball(invalidNumber) }
                .message shouldBe "로또 숫자는 1 이상 45 이하입니다"
        }
    }

    "로또는 중복되지 않는 여섯개의 숫자로 생성해야 한다" {
        listOf(
            listOf(1, 2, 3, 4, 5),
            listOf(1, 2, 3, 4, 5, 6, 7),
            listOf(1, 2, 3, 4, 5, 5)
        ).forAll { numbers ->
            shouldThrow<IllegalArgumentException> { Lotto(numbers.map { Ball(it) }) }
                .message shouldBe "로또는 중복되지 않는 6개 숫자로 구성되어야 합니다"
        }
    }

    "우승 로또의 보너스 숫자는 로또 숫자와 중복되지 않아야 한다" {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { Ball(it) })

        shouldThrow<IllegalArgumentException> { WinningLotto(lotto, Ball(1)) }
            .message shouldBe "보너스 숫자는 로또 번호와 중복될 수 없습니다"
    }
})
