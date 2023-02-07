import domain.Alphabet
import domain.AlphabetsPool
import domain.Compared.*
import domain.Word
import domain.Wordle
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class WordTest: StringSpec({

    "단어는 Wordle에서 지정한 크기로 구성해야 한다" {
        listOf("java", "kotlin").forAll { invalidValue ->
            shouldThrow<IllegalArgumentException> { Word(invalidValue) }
                .message shouldBe "단어는 ${Wordle.WORD_LENGTH} 글자로 구성됩니다"
        }
    }

    "단어는 동일한 문자열이면 같은 객체로 취급한다" {
        (Word("apple") == Word("apple")) shouldBe true
    }
})

class WordCompareTest: BehaviorSpec({

    Given("'apple'이 단어로 주어졌을 때") {
        val answer = Word("apple")

        When("'widow'로 비교하면") {
            val guess = Word("widow")
            val result = answer.compare(guess)

            Then("결과로 NONE, NONE, NONE, NONE, NONE 이 나온다") {
                result shouldBe listOf(NONE, NONE, NONE, NONE, NONE)
            }
        }

        When("'awake'로 비교하면") {
            val guess = Word("awake")
            val result = answer.compare(guess)

            Then("결과로 EQUAL, NONE, NONE, NONE, EQUAL 이 나온다") {
                result shouldBe listOf(EQUAL, NONE, NONE, NONE, EQUAL)
            }
        }

        When("'AWAKE'로 비교하면") {
            val guess = Word("AWAKE")
            val result = answer.compare(guess)

            Then("결과로 EQUAL, NONE, NONE, NONE, EQUAL 이 나온다") {
                result shouldBe listOf(EQUAL, NONE, NONE, NONE, EQUAL)
            }
        }

        When("'pride'로 비교하면") {
            val guess = Word("pride")
            val result = answer.compare(guess)

            Then("결과로 EXIST, NONE, NONE, NONE, EQUAL 이 나온다") {
                result shouldBe listOf(EXIST, NONE, NONE, NONE, EQUAL)
            }
        }

        When("'apple로 비교하면") {
            val guess = Word("apple")
            val result = answer.compare(guess)

            Then("결과로 EQUAL, EQUAL, EQUAL, EQUAL, EQUAL 이 나온다") {
                result shouldBe listOf(EQUAL, EQUAL, EQUAL, EQUAL, EQUAL)
            }
        }
    }

    Given("'silly'이 단어로 주어졌을 때") {
        val answer = Word("silly")

        When("'lilly'로 비교하면") {
            val guess = Word("lilly")
            val result = answer.compare(guess)

            Then("결과로 NONE, NONE, NONE, NONE, NONE 이 나온다") {
                result shouldBe listOf(NONE, EQUAL, EQUAL, EQUAL, EQUAL)
            }
        }
    }
})

class AlphabetsPoolTest: BehaviorSpec({

    Given("has 메서드는") {
        val apple = "apple"
        val applePool = AlphabetsPool(apple.map { Alphabet(it) })

        When("해당 알파벳이 키에 없으면") {
            val z = Alphabet('z')

            Then("false를 반환한다") {
                applePool.has(z) shouldBe false
            }
        }

        When("해당 알파벳이 키에 있고, 값이 1 이상이면") {
            val a = Alphabet('a')
            val p = Alphabet('p')

            Then("true를 반환한다") {
                applePool.has(a) shouldBe true
                applePool.has(p) shouldBe true
            }
        }

        When("해당 알파벳이 키에 있고, 값이 0 이하면") {
            val a = Alphabet('a')
            applePool.spend(a)

            Then("false를 반환한다") {
                applePool.has(a) shouldBe false
            }
        }
    }
})

class AlphabetTest: StringSpec({

    "값으로 영어 알파벳만 가질 수 있다" {
        listOf('1', '가', 'ㄱ', '!', ' ', '\t').forAll { invalidValue ->
            shouldThrow<IllegalArgumentException> { Alphabet(invalidValue) }
                .message shouldBe "값은 영어 알파벳이어야 합니다"
        }
    }

    "대소문자에 관계없이 같은 값으로 취급한다" {
        (Alphabet('a') == Alphabet('A')) shouldBe true
    }
})
