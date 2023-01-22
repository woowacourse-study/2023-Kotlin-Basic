package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row

internal class AnswerTest : StringSpec({

    "Answer length should be 5" {
        forAll(
            row("abcd"),
            row("abcdef")
        ) { given ->
            shouldThrow<IllegalArgumentException> {
                Answer.from(given)
            }
        }
    }

    "Answer should contain only alphabet characters" {
        forAll(
            row("abcd1"),
            row("!abcd")
        ) { given ->
            shouldThrow<java.lang.IllegalArgumentException> {
                Answer.from(given)
            }
        }
    }
})
