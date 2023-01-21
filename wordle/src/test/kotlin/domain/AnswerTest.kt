package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AnswerTest : StringSpec({

    "정답이 유효한 단어가 아니면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { Answer(Word("xxxxx")) }
            .message shouldBe "정답은 유효한 단어여야 합니다."
    }
})
