package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class LottoNumberTest : StringSpec({
    "로또 번호가 1 이상 45 이하가 아니면 예외를 발생시킨다." {
        listOf(0, 46).forEach {
            shouldThrow<IllegalArgumentException> { LottoNumber(it) }
                .message shouldBe "로또 번호는 1 이상 45 이하여야 합니다."
        }
    }
})
