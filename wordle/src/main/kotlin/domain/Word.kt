package domain

private const val WORD_LENGTH = 5

class Word(
    word: String
) {
    val letters: List<Letter>

    init {
        if (word.length != WORD_LENGTH) {
            throw IllegalArgumentException("글자수는 반드시 5글자여야 합니다. (입력: ${word.length}글자)")
        }

        letters = word.mapIndexed { index, string ->
            Letter(string.toString(), index + 1)
        }.toList()
    }

    // TODO: 리팩토링
    fun compareWithCorrectAnswer(correctAnswer: Word): List<LetterCompareResult> {
        val letterCompareResults = mutableListOf<LetterCompareResult>()

        for (letter in letters) {
            var letterCompareResult = LetterCompareResult.GRAY

            for (correctLetter in correctAnswer.letters) {
                val result = letter.compare(correctLetter)

                if (result == LetterCompareResult.YELLOW) {
                    if (letterCompareResult == LetterCompareResult.GREEN) {
                        break
                    }

                    letterCompareResult = result
                }

                if (result == LetterCompareResult.GREEN) {
                    letterCompareResult = result
                }
            }

            letterCompareResults.add(letterCompareResult)
        }

        return letterCompareResults
    }
}
