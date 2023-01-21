package domain

import domain.Color.GRAY
import domain.Color.GREEN
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class JudgementTileTest : StringSpec({

    "타일이 5칸이 아니면 예외를 발생시킨다." {
        listOf(
            listOf(GREEN, GREEN, GREEN, GREEN),
            listOf(GREEN, GREEN, GREEN, GREEN, GREEN, GREEN)
        ).forAll {
            shouldThrow<IllegalArgumentException> { JudgementTile(it) }
                .message shouldBe "타일은 5칸이어야 합니다."
        }
    }

    "정답을 모두 맞혀 모든 타일이 초록색인지 판별할 수 있다." {
        listOf(
            row(listOf(GREEN, GREEN, GREEN, GREEN, GREEN), true),
            row(listOf(GREEN, GREEN, GREEN, GREEN, GRAY), false)
        ).forAll { (colors, expected) ->
            run {
                val judgementTile = JudgementTile(colors)

                val actual = judgementTile.isAllGreen()

                actual shouldBe expected
            }
        }
    }
})
