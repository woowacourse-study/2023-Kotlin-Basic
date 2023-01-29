package domain

import domain.LetterCompareResult.*

private const val WORD_LENGTH = 5

class Word(
    word: String
) {
    val letters: List<Letter>

    init {
        if (word.length != WORD_LENGTH) {
            throw IllegalArgumentException("글자수는 반드시 5글자여야 합니다. (입력: ${word.length}글자)")
        }

        letters = word.map { letter -> Letter(letter.toString()) }.toList()
    }

    // TODO: 리팩토링
    fun compareWithCorrectAnswer(correctAnswer: Word): LetterCompareResults {
        val answerLetters = ArrayList<Letter?>(correctAnswer.letters)
        val submitLetters = this.letters

        val tiles = mutableListOf(GRAY, GRAY, GRAY, GRAY, GRAY)
        for (i in 0..4) {
            val currentLetter = submitLetters[i]
            if (currentLetter == answerLetters[i]) {
                tiles[i] = GREEN
            }
        }

        for (i in 0..4) {
            val currentTile = tiles[i]
            if (currentTile == GREEN) {
                continue
            }

            val currentLetter = submitLetters[i]
            for ((j, letter) in answerLetters.withIndex()) {
                if (tiles[j] == GREEN) continue
                if (currentLetter == letter) {
                    tiles[i] = YELLOW
                }
            }
        }

        return LetterCompareResults(tiles)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Word

        if (letters != other.letters) return false

        return true
    }

    override fun hashCode(): Int {
        return letters.hashCode()
    }

    override fun toString(): String {
        return "Word(letters=$letters)"
    }
}
