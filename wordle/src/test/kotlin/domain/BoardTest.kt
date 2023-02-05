package domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

internal class BoardTest : BehaviorSpec({

    Given("possible word list is given") {
        val possibleWords = listOf("cigar", "rebut", "sissy", "humph", "awake")
        val answer = Wordle.from("cigar")
        val board = Board.of(answer, possibleWords)

        When("try answer is not in possible words") {
            val tryAnswer = "abcde"

            Then("throw exception") {
                shouldThrow<IllegalArgumentException> {
                    board.tryAnswer(Wordle.from(tryAnswer))
                }
            }
        }

        When("try answer is in possible words") {
            val tryAnswer = "rebut"

            Then("should not throw exception") {
                shouldNotThrow<IllegalArgumentException> {
                    board.tryAnswer(Wordle.from(tryAnswer))
                }
            }
        }
    }
})
