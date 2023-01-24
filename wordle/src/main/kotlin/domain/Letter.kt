package domain

private const val LETTER_LENGTH = 1

private const val MIN_POSITION = 1
private const val MAX_POSITION = 6

class Letter(
    val letter: String,
    val position: Int
) {
    init {
        validateLetterLength()
        validatePosition()
    }

    private fun validateLetterLength() {
        if (letter.length != LETTER_LENGTH) {
            throw IllegalArgumentException("글자는 반드시 1글자여야 합니다. (입력: ${letter.length}글자)")
        }
    }

    private fun validatePosition() {
        if (position !in MIN_POSITION..MAX_POSITION) {
            throw IllegalArgumentException("위치는 반드시 1~6 사이여야 합니다. (입력: ${position})")
        }
    }

    fun compare(other: Letter): LetterCompareResult {
        val isLetterMatched = letter == other.letter
        val isPositionMatched = position == other.position

        return when {
            isLetterMatched && isPositionMatched -> LetterCompareResult.GREEN
            isLetterMatched && !isPositionMatched -> LetterCompareResult.YELLOW
            else -> LetterCompareResult.GRAY
        }
    }
}
