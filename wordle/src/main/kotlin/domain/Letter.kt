package domain

private const val LETTER_LENGTH = 1

data class Letter(
    val letter: String
) {
    init {
        validateLetterLength()
    }

    private fun validateLetterLength() {
        if (letter.length != LETTER_LENGTH) {
            throw IllegalArgumentException("글자는 반드시 1글자여야 합니다. (입력: ${letter.length}글자)")
        }
    }
}
