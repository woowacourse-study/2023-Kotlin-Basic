package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row

class LetterTest : StringSpec({

    "letter이 한글자가 아니라면 예외를 던진다." {
        forAll(
            row("aa"),
            row(""),
            row("abcdef"),
        ) {
            shouldThrow<IllegalArgumentException> {
                Letter(it)
            }
        }
    }
})
