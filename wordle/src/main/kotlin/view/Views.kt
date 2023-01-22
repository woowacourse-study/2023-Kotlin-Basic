package view

import domain.Answer
import domain.Color
import domain.JudgementTile
import domain.Wordle

fun inputPrediction(): String {
    println("정답을 입력해주세요.")
    return readln()
}

fun printStartMessage() {
    println(
        """
        WORDLE을 ${Wordle.MAX_ROUND}번 만에 맞춰 보세요.
        시도의 결과는 타일의 색 변화로 나타납니다.
        """.trimIndent()
    )
}

fun printTiles(judgementTiles: List<JudgementTile>) {
    println()
    judgementTiles.forEach(::printTile)
    println()
}

private fun printTile(judgementTile: JudgementTile) {
    judgementTile.colors
        .forEach(::printColor)
    println()
}

private fun printColor(color: Color) {
    when (color) {
        Color.GRAY -> print("⬜")
        Color.YELLOW -> print("🟨")
        Color.GREEN -> print("🟩")
    }
}

fun printTryCount(currentRound: Int) {
    println(
        """
        
        $currentRound/${Wordle.MAX_ROUND}
        """.trimIndent()
    )
}

fun printFailMessage(answer: Answer) {
    println(
        """
            
        ${Wordle.MAX_ROUND}번의 기회를 모두 소진했습니다.
        정답은 $answer 입니다.
        내일 다시 도전해주세요.
        """.trimIndent()
    )
}
