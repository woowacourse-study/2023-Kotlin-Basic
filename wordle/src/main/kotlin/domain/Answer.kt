package domain

class Answer(
    private val word: Word
) {

    init {
        require(ValidWords.contains(word)) { "정답은 유효한 단어 목록에 포함된 단어어야 합니다." }
    }

    fun judge(prediction: Prediction): JudgementTile {
        val charCounts: MutableMap<Char, Int> = word.groupBy { it }
            .mapValues { it.value.count() }
            .toMutableMap()
        val colors = MutableList(word.length) { Color.GRAY }
        paintGreen(colors, prediction, charCounts)
        paintYellow(colors, prediction, charCounts)
        return JudgementTile(colors)
    }

    private fun paintGreen(colors: MutableList<Color>, prediction: Prediction, charCounts: MutableMap<Char, Int>) {
        (0 until word.length).forEach {
            val char = prediction.word[it]
            if (word[it] == char) {
                colors[it] = Color.GREEN
                decreaseCharCount(charCounts, char)
            }
        }
    }

    private fun paintYellow(colors: MutableList<Color>, prediction: Prediction, charCounts: MutableMap<Char, Int>) {
        (0 until word.length).forEach {
            val char = prediction.word[it]
            if (colors[it] != Color.GREEN && charCounts.contains(char)) {
                colors[it] = Color.YELLOW
                decreaseCharCount(charCounts, char)
            }
        }
    }

    private fun decreaseCharCount(charCounts: MutableMap<Char, Int>, char: Char) {
        charCounts.computeIfPresent(char) { _, count -> count - 1 }
        if (charCounts[char] == 0) {
            charCounts.remove(char)
        }
    }

    override fun toString(): String {
        return "$word"
    }
}
