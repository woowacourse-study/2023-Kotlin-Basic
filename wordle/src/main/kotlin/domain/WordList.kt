package domain

class WordList(
    wordsText: String
) {
    val values: List<Word>

    init {
        values = wordsText.split("\n").map { Word(it) }
    }
}
