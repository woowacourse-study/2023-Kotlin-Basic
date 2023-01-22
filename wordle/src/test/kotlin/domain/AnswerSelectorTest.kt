package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.time.LocalDate

internal class AnswerSelectorTest : StringSpec({

    "AnswerSelector returns word of i-th index word in list" {
        val words = listOf("cigar", "rebut","sissy", "humph")

        forAll(
            row(LocalDate.of(2022, 6, 19), "cigar"),
            row(LocalDate.of(2022, 6, 20), "rebut"),
        ) {
            today, expected ->
            AnswerSelector.findTodayAnswer(today, words) shouldBe expected
        }
    }
})
