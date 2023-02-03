package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class WordTest : StringSpec({

    "단어 길이가 5자가 아니면 예외를 발생시킨다." {
        listOf("book", "banana")
            .forAll {
                shouldThrow<IllegalArgumentException> { Word(it) }.message shouldBe "단어는 5자여야 합니다."
            }
    }
})
