package domain

import domain.Color.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AnswerTest : StringSpec({

    "정답이 유효한 단어가 아니면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { Answer(Word("xxxxx")) }
            .message shouldBe "정답은 유효한 단어 목록에 포함된 단어어야 합니다."
    }

    "결과를 판정할 수 있다. - 정답과 예측이 완전히 일치하는 경우" {
        val answer = Answer(Word("cigar"))
        val prediction = Prediction("cigar")

        val judgementTile = answer.judge(prediction)

        judgementTile.isAllGreen() shouldBe true
    }

    "결과를 판정할 수 있다. - 글자와 위치가 모두 일치하는 글자가 있는 경우" {
        val answer = Answer(Word("cigar"))
        val prediction = Prediction("comet")

        val judgementTile = answer.judge(prediction)

        judgementTile shouldBe JudgementTile(listOf(GREEN, GRAY, GRAY, GRAY, GRAY))
    }

    "결과를 판정할 수 있다. - 글자는 일치하지만 위치가 다른 글자가 있는 경우" {
        val answer = Answer(Word("cigar"))
        val prediction = Prediction("heath")

        val judgementTile = answer.judge(prediction)

        judgementTile shouldBe JudgementTile(listOf(GRAY, GRAY, YELLOW, GRAY, GRAY))
    }

    "결과를 판정할 수 있다. - 동일한 글자를 두 개 이상 입력하고 정답에 다른 위치로 하나만 포함되어 있을 경우" {
        val answer = Answer(Word("react"))
        val prediction = Prediction("carry")

        val judgementTile = answer.judge(prediction)

        judgementTile shouldBe JudgementTile(listOf(YELLOW, YELLOW, YELLOW, GRAY, GRAY))
    }
})
