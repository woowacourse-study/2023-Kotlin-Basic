package view

fun inputPrediction(): String {
    println("정답을 입력해주세요.")
    return readln()
}

fun printStartMessage() {
    println(
        """
        WORDLE을 6번 만에 맞춰 보세요.
        시도의 결과는 타일의 색 변화로 나타납니다.
        """.trimIndent()
    )
}
