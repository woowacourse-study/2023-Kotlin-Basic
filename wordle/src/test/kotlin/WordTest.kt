import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class WordTest: StringSpec({

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
