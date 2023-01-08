package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.string.match

internal class LottoNumberTest : StringSpec({
    "로또 번호가 1 이상 45 이하가 아니면 예외를 발생시킨다." {
        listOf(0, 46).forEach {
            shouldThrow<IllegalArgumentException> { LottoNumber(it) }
                .message should match("로또 번호는 1 이상 45 이하여야 합니다.")
        }
    }
})
