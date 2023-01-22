package domain

import domain.Color.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class WordleTest : StringSpec({

    "정답을 맞히면 게임이 끝난 상태가 된다." {
        val wordle = Wordle { Answer(Word("cigar")) }

        wordle.proceedRound("cigar")

        wordle.isEnd shouldBe true
    }

    "6번의 라운드를 모두 진행하면 게임이 끝난 상태가 된다." {
        val wordle = Wordle { Answer(Word("cigar")) }

        repeat((1..6).count()) { wordle.proceedRound("apple") }

        wordle.isEnd shouldBe true
    }

    "라운드가 진행되는 동안의 타일 판정 결과를 저장하고 있다." {
        val wordle = Wordle { Answer(Word("cigar")) }

        wordle.proceedRound("rebut")
        wordle.proceedRound("awake")
        val actual = wordle.proceedRound("cigar")

        val expected = listOf(
            JudgementTile(listOf(YELLOW, GRAY, GRAY, GRAY, GRAY)),
            JudgementTile(listOf(YELLOW, GRAY, GRAY, GRAY, GRAY)),
            JudgementTile(listOf(GREEN, GREEN, GREEN, GREEN, GREEN))
        )
        actual shouldContainExactly expected
    }

    "이미 종료된 게임을 진행할 수 없다." {
        val wordle = Wordle { Answer(Word("cigar")) }

        wordle.proceedRound("cigar")

        shouldThrow<IllegalArgumentException> { wordle.proceedRound("cigar") }
            .message shouldBe "이미 게임이 종료되었습니다."
    }
})
