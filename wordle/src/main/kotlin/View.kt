import domain.Compared
import domain.Wordle

fun outputIntro() {
    println("WORDLE을 ${Wordle.MAX_TRIAL}번 만에 맞춰 보세요.")
    println("시도의 결과는 타일의 색 변화로 나타납니다.")
}

fun outputGuessResults(results: List<List<Compared>>) {
    println("\n${convertToEmoji(results)}\n")
}

fun outputWordleResult(remainTrial: Int, results: List<List<Compared>>) {
    println("${Wordle.MAX_TRIAL - remainTrial}/${Wordle.MAX_TRIAL}")
    println(convertToEmoji(results))
}

private fun convertToEmoji(results: List<List<Compared>>) = results.joinToString(separator = "\n") { it ->
    it.joinToString(separator = "") {
        when (it) {
            Compared.EQUAL -> "🟩"
            Compared.EXIST -> "🟨"
            else -> "⬜️"
        }
    }
}

fun inputWord(): String {
    println("정답을 맞춰보세요.")
    return readln()
}
