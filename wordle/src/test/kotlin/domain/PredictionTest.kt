package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PredictionTest : StringSpec({

    "예측으로 제출한 단어가 유효한 단어가 아니면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { Prediction("xxxxx") }
            .message shouldBe "유효한 단어 목록에 있는 단어로 정답을 예측해야 합니다."
    }
})
