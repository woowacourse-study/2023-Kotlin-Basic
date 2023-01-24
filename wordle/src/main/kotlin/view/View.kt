package view

import domain.*

private const val TOTAL_ROUND: Int = 6

fun printStartMessage() {
    println("""
        WORDLE을 6번 만에 맞춰 보세요.
        시도의 결과는 타일의 색 변화로 나타납니다.
    """.trimIndent())
}

fun printRoundCount(round: Int) {
    println("${round}/${TOTAL_ROUND}")
}

fun printAnswer(answer: Answer) {
    println("오늘의 정답: ${answer.value}")
}

fun printAllRoundResults(gameResult: GameResult) {
    for (result in gameResult) {
        printRoundResult(result)
    }
}

fun printRoundResult(result: RoundResult) {
    for (tile in result) {
        when (tile) {
            Tile.GREEN -> print("\uD83D\uDFE9")
            Tile.YELLOW -> print("\uD83D\uDFE8")
            Tile.GREY -> print("⬜")
        }
    }
    println()
}

fun inputWord(): Word {
    println("정답을 입력해주세요.")
    val word = readLine() ?: throw IllegalArgumentException("정답을 입력해주세요!")
    return Word(word)
}

fun inputWordWithRetry(): Word = try {
    inputWord()
} catch (e: IllegalArgumentException) {
    println(e.message)
    inputWordWithRetry()
}

