package domain

private const val LINE_SEPARATOR = "\n"

class WordList(
    wordsText: String
) {
    val values: List<Word>

    init {
        values = wordsText.split(LINE_SEPARATOR).map { Word(it) }
    }

    fun contains(word: Word): Boolean {
        return word in values
    }
}
