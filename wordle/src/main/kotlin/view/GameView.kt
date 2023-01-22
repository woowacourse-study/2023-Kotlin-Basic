package view

import domain.Wordle

fun printGameStart() {
    println(
        """
        WORDLE을 6번 만에 맞춰 보세요.
        시도의 결과는 타일의 색 변화로 나타납니다.
        """.trimIndent()
    )
}

fun inputAnswer(): String {
    println("정답을 입력해 주세요.")
    return readln()
}

fun printTryResult(result: List<String>) {
    println(result)
}

fun printGameResult(win: Boolean, answer: Wordle) {
    println("승 : $win")
    println("정답: $answer")
}
