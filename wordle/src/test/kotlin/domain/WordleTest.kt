package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class WordleTest : StringSpec({

    "Answer length should be 5" {
        forAll(
            row("abcd"),
            row("abcdef")
        ) { given ->
            shouldThrow<IllegalArgumentException> {
                Wordle.from(given)
            }
        }
    }

    "Answer should contain only alphabet characters" {
        forAll(
            row("abcd1"),
            row("!abcd")
        ) { given ->
            shouldThrow<java.lang.IllegalArgumentException> {
                Wordle.from(given)
            }
        }
    }

    "marks answer correctly" {
        forAll(
            row("cigar", "cabcd", "GYWYW"),
            row("cigar", "karma", "WYYWY"),
            row("cigar", "hunky", "WWWWW"),
            row("cigar", "cigar", "GGGGG"),
            row("cigar", "musty", "WWWWW"),
        ) { answer, tryAnswer, result ->
            Wordle.from(answer).markAnswer(Wordle.from(tryAnswer)) shouldBe result
        }
    }
})
