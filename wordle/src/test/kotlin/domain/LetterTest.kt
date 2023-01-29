package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LetterTest : StringSpec({

    "compare 메소드는 글자가 위치가 동일하고, 글자가 동일하면 GREEN을 반환한다." {
        val letter1 = Letter("A", 1)
        val letter2 = Letter("A", 1)

        val actual = letter1.compare(letter2)

        actual shouldBe LetterCompareResult.GREEN
    }

    "compare 메소드는 글자가 위치가 다르고, 글자가 동일하면 YELLOW를 반환한다." {
        val letter1 = Letter("A", 1)
        val letter2 = Letter("A", 3)

        val actual = letter1.compare(letter2)

        actual shouldBe LetterCompareResult.YELLOW
    }

    "compare 메소드는 글자가 위치가 다르고, 글자가 다르면 GRAY를 반환한다." {
        val letter1 = Letter("A", 1)
        val letter2 = Letter("B", 3)

        val actual = letter1.compare(letter2)

        actual shouldBe LetterCompareResult.GRAY
    }

    "position은 1~5 사이가 아니라면 예외를 던진다." {
        forAll(
            row(-1),
            row(0),
            row(6),
        ) { it ->
            shouldThrow<IllegalArgumentException> {
                Letter("A", it)
            }
        }
    }

    "letter이 한글자가 아니라면 예외를 던진다." {
        forAll(
            row("aa"),
            row(""),
            row("abcdef"),
        ) { it ->
            shouldThrow<IllegalArgumentException> {
                Letter(it, 1)
            }
        }
    }
})
