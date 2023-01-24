package view

import domain.Word

fun printStartMessage() {
    println("""
        WORDLE을 6번 만에 맞춰 보세요.
        시도의 결과는 타일의 색 변화로 나타납니다.
    """.trimIndent())
}

fun inputWord(): Word {
    println("정답을 입력해주세요.")
    val word = readLine() ?: throw IllegalArgumentException("정답을 입력해주세요!")
    return Word(word)
}
