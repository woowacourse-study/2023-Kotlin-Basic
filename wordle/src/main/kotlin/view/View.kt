package view

import domain.Tiles

fun inputText(): String {
    return readLine() ?: ""
}

fun printTitleMessage() {
    println("WORDLE을 6번 만에 맞춰 보세요.\n시도의 결과는 타일의 색 변화로 나타납니다.")
}

fun printSubmitWordMessage() {
    println("정답을 입력해 주세요.")
}

fun printLetterCompareResults(tilesList: List<Tiles>) {
    println()
    for (singleResult in tilesList) {
        println(singleResult.values.joinToString("") { it.character })
    }
    println()
}

fun printSuccessMessage(currentRound: Int) {
    println("${currentRound}/6")
}

fun printFailMessage() {
    println("주어진 6번의 기회안에 정답을 맞추지 못했습니다.")
}
