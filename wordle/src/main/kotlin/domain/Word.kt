package domain

import domain.Tile.*

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

    fun compareWithCorrectAnswer(correctAnswer: Word): Tiles {
        val answerLetters = ArrayList<Letter?>(correctAnswer.letters)

        var tiles = listOf(GRAY, GRAY, GRAY, GRAY, GRAY)
        tiles = judgeGreen(tiles, answerLetters)
        tiles = judgeYellow(tiles, answerLetters)

        return Tiles(tiles)
    }

    private fun judgeGreen(
        tiles: List<Tile>,
        answerLetters: ArrayList<Letter?>
    ): List<Tile> {
        return tiles.mapIndexed { i, tile ->
            if (letters[i] == answerLetters[i]) {
                GREEN
            } else {
                tile
            }
        }
    }

    private fun judgeYellow(
        tiles: List<Tile>,
        answerLetters: ArrayList<Letter?>
    ): List<Tile> {
        val copiedTiles = tiles.toMutableList()

        for (i in 0..4) {
            if (tiles[i] == GREEN) {
                continue
            }

            val currentLetter = letters[i]
            for ((j, letter) in answerLetters.withIndex()) {
                val sameInOtherPosition = currentLetter == letter
                val alreadyGreen = tiles[j] == GREEN

                if (sameInOtherPosition && !alreadyGreen) {
                    copiedTiles[i] = YELLOW
                }
            }
        }

        return copiedTiles
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
