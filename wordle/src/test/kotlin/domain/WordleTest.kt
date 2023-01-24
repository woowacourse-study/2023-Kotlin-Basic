package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class WordleTest: BehaviorSpec({

    Given("guess는") {

        When("시도 가능 횟수가 0 이하면") {
            val wordle = Wordle(Word("apple"))
            for (i in 1..Wordle.MAX_TRIAL) wordle.guess(Word("break"))

            Then("호출 시 예외를 던진다") {
                shouldThrow<IllegalStateException> { wordle.guess(Word("break")) }
                    .message shouldBe "비교 가능 횟수가 남아있지 않습니다"
            }
        }

        When("이미 정답을 맞췄다면") {
            val wordle = Wordle(Word("apple"))
            wordle.guess(Word("apple"))

            Then("호출 시 예외를 던진다") {
                shouldThrow<IllegalStateException> { wordle.guess(Word("break")) }
                    .message shouldBe "이미 정답을 맞췄습니다"
            }
        }
    }

    Given("isEnded는") {

        When("시도 가능 횟수가 1 이상이고 정답을 맞춘 적이 없다면") {
            val wordle = Wordle(Word("apple"))
            val isEnded = wordle.isEnded()

            Then("false를 반환한다") {
                isEnded shouldBe false
            }
        }

        When("시도 가능 횟수가 1 이상이고 정답을 맞춘 적이 있다면") {
            val wordle = Wordle(Word("apple"))
            wordle.guess(Word("apple"))
            val isEnded = wordle.isEnded()

            Then("true를 반환한다") {
                isEnded shouldBe true
            }
        }

        When("시도 가능 횟수가 0 이하면 맞췄는지 관계없이") {
            val wordle = Wordle(Word("apple"))
            for (i in 1..Wordle.MAX_TRIAL) wordle.guess(Word("break"))
            val isEnded = wordle.isEnded()

            Then("true를 반환한다") {
                isEnded shouldBe true
            }
        }
    }
})
