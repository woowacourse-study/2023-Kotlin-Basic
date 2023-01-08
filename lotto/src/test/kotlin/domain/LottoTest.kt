package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class LottoTest : StringSpec({
    "로또 번호가 6개가 아니면 예외를 발생시킨다." {
        listOf(
            listOf(1, 2, 3, 4, 5),
            listOf(1, 2, 3, 4, 5, 6, 7)
        ).forEach {
            shouldThrow<IllegalArgumentException> { Lotto.from(it) }
                .message shouldBe "로또 번호는 6개여야 합니다."
        }
    }

    "로또 번호에 중복이 있으면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { Lotto.from(listOf(1, 1, 2, 3, 4, 5)) }
            .message shouldBe "로또 번호에 중복이 있을 수 없습니다."
    }

    "로또가 특정 번호를 포함하고 있는 지 확인할 수 있다." {
        val lotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))

        lotto.contains(LottoNumber(6)) shouldBe true
    }

    "다른 로또와 일치한 번호 개수를 구할 수 있다." {
        val lotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val target = Lotto.from(listOf(1, 2, 3, 4, 5, 7))

        lotto.countMatches(target) shouldBe 5
    }
})
