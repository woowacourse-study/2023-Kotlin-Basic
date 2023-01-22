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
        for (index in 0 until word.length) {
            val char = prediction.word[index]
            if (word[index] == char) {
                colors[index] = Color.GREEN
                decreaseCharCount(charCounts, char)
            }
        }
    }

    private fun paintYellow(colors: MutableList<Color>, prediction: Prediction, charCounts: MutableMap<Char, Int>) {
        for (index in 0 until word.length) {
            val char = prediction.word[index]
            if (colors[index] != Color.GREEN && charCounts.contains(char)) {
                colors[index] = Color.YELLOW
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
